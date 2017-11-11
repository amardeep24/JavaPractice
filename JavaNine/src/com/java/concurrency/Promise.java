package com.java.concurrency;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Promise {

    private final static Logger LOG = Logger.getLogger(Reactive.class.getName());

    public static void main(String args[]){
        CompletableFuture<String> result=getResource();
        result.thenApply(data->{
            LOG.log(Level.INFO,data);
            return new Payload().setData(data);
        }).thenAccept(data->{
            data.processPayload();
        });
        while(true){
            try {
                Thread.sleep(1000);
                LOG.log(Level.INFO, "processing");
            }catch(InterruptedException ie){
                LOG.log(Level.SEVERE,"Interrupted",ie);
            }
        }

    }
    public static CompletableFuture<String> getResource(){
        CompletableFuture<String> promise;
        LOG.log(Level.INFO,"Triggered Job");
        promise=CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                LOG.log(Level.SEVERE,"Interrupted:",e);
            }
            return "Data from parallel task";
        });
        return promise;
    }
}
class Payload{

    private final static Logger LOG = Logger.getLogger(Reactive.class.getName());

    private String data;
    private long id=1000;

    public long getId() {
        return id;
    }

    public Payload setId(long id) {
        this.id = id;
        return this;
    }

    public String getData() {
        return data;
    }

    public Payload setData(String data) {
        this.data = data;
        return this;
    }

    public void processPayload(){
      LOG.log(Level.INFO,this.getData().toUpperCase());
    }

}
