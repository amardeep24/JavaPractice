package com.java.concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.logging.Level;
import java.util.logging.Logger;

//Commented by Jay
public class Reactive {

    private final static Logger LOG = Logger.getLogger(Reactive.class.getName());

    public static void main(String []args){
        SubmissionPublisher<String> publisher=new SubmissionPublisher<>();
        FirstSubscriber<String> subscriberOne=new FirstSubscriber<>();
        SecondSubscriber<String> subscriberTwo=new SecondSubscriber<>();
        publisher.subscribe(subscriberOne);
        publisher.subscribe(subscriberTwo);

        LOG.log(Level.INFO,"Publishing messages, write ':q' to quit or ':w' to publish ");


        List<String> inputData=new ArrayList<>();
        while(true){
           // List.of("A","B","C").forEach(publisher::submit);

            try {
                String data=new BufferedReader(new InputStreamReader(System.in)).readLine();
                if(":q".equals(data)){
                    break;
                }else if(":w".equals(data)){
                    publishMessages(inputData,publisher);
                }else{
                    inputData.add(data);
                }


            }catch (IOException e){
                LOG.log(Level.SEVERE,"Error reading input: ",e);
            }

        }

        publisher.close();

    }
    public static void publishMessages(List<String> inputData,SubmissionPublisher<String> publisher){
        inputData.stream().forEach(publisher::submit);
    }

}
class FirstSubscriber<T> implements Flow.Subscriber<T>{

    private final static Logger LOG = Logger.getLogger(FirstSubscriber.class.getName());
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription sub) {
        this.subscription=sub;
        this.subscription.request(Long.MAX_VALUE);

    }

    @Override
    public void onNext(T item) {
        LOG.log(Level.INFO,"From first sub: "+String.valueOf(item));
       this.subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onError(Throwable throwable) {
        LOG.log(Level.SEVERE,"Error in first sub: "+throwable.getMessage());
    }

    @Override
    public void onComplete() {
        LOG.log(Level.INFO,"First sub completed");
    }
}
class SecondSubscriber<T> implements Flow.Subscriber<T>{

    private final static Logger LOG = Logger.getLogger(SecondSubscriber.class.getName());
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription sub) {
        this.subscription=sub;
        this.subscription.request(Long.MAX_VALUE);

    }

    @Override
    public void onNext(T item) {
        LOG.log(Level.INFO,"From second sub: "+String.valueOf(item));
        this.subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onError(Throwable throwable) {
        LOG.log(Level.SEVERE,"Error in second sub: "+throwable.getMessage());
    }

    @Override
    public void onComplete() {
        LOG.log(Level.INFO,"Second sub completed");
    }
}
class FirstProcessor implements Flow.Processor<String,String>{
    @Override
    public void subscribe(Flow.Subscriber subscriber) {

    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

    }

    @Override
    public void onNext(String item) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
