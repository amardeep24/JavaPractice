package com.java.concurrency;

import java.io.*;

public interface Encapsulation {
    //Java 9
    private int testPrivateMethod(){
        System.out.println("private method");
        return 1;
    }
    //Java 9
    private static int testStaticPrivateMethod(){
        System.out.println("private static method");
        return 1;
    }
    static int testStaticMethod(){
        System.out.println("static method");
        return 1;
    }
    default int testDefaultMethod(){
        testPrivateMethod();
        System.out.println("default method");
        return 1;
    }
}
class EncapsulationImpl implements Encapsulation{

    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void readData(){
        try(reader){
            reader.readLine();
        }catch(IOException e){
            e.printStackTrace();;
        }
    }

}
class EncapsulationDemo {
    public static void main(String []args){
        Encapsulation encap=new EncapsulationImpl();
        encap.testDefaultMethod();

        Encapsulation.testStaticMethod();

    }
}