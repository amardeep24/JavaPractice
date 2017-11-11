import java.util.*;
import java.util.stream.Collectors;

public class MapReduce {
    public static void main(String args[]){
        User amardeep=new User();
        amardeep.setAge(26);
        amardeep.setName("Amardeep");
        amardeep.setSex(Sex.MALE);
        User debasish=new User();
        debasish.setAge(65);
        debasish.setName("Debasish");
        debasish.setSex(Sex.MALE);
        User sarmila=new User();
        sarmila.setAge(60);
        sarmila.setName("Sarmila");
        sarmila.setSex(Sex.FEMALE);

        List<User> users= List.of(amardeep,debasish,sarmila);
        //Using sum
        int totalSumOfAge=users.stream().mapToInt(User::getAge).sum();
        //Using reduce
        int totalAge=users.stream().mapToInt(User::getAge).reduce(0,(a,b)->a+b);
        //average age
        double averageAge = users.stream().mapToInt(User::getAge).average().getAsDouble();
        //groupBy
        Map<Sex,List<User>> usersByGender=users.stream().collect(Collectors.groupingBy(User::getSex));
        usersByGender.forEach((a,b)->{
            System.out.println("Sex: "+a+ " Users: "+b.stream().map(User::getName).collect(Collectors.toList()));
        });
        //groupBy Multilevel names
        Map<Sex,List<String>> userNamesByGender=users.stream().collect(Collectors.groupingBy(User::getSex,Collectors.mapping(User::getName,Collectors.toList())));
        userNamesByGender.forEach((a,b)->{
            System.out.println("Sex: "+a+"Name: "+b.toString());
        });
        //groupBy Multilevel sum
        Map<Sex,Integer> gendersByAge=users.stream().collect(Collectors.groupingBy(User::getSex,Collectors.reducing(0,User::getAge,Integer::sum)));
        gendersByAge.forEach((a,b)->{
            System.out.println("Sex: "+a+"Sum of Age: "+b);
        });
        //groupBy Multilevel average
        Map<Sex,Double> genderAverageAge=users.stream().collect(Collectors.groupingBy(User::getSex,Collectors.averagingDouble(User::getAge)));
        genderAverageAge.forEach((a,b)->{
            System.out.println("Sex: "+a+"Average of age: "+b);
        });
        //Null handle
        List<String> dataList= new ArrayList<>(List.of("abc","def","efg"));
        dataList.add(null);
        dataList.stream().filter(Objects::nonNull).map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);

    }
}
class User{
    private String name;
    private int age;
    private Sex sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }


    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }


}
enum Sex{
    MALE,FEMALE;
}
