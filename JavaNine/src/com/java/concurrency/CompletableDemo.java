package com.java.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableDemo {

    public static void main(String args[]){

        try{
            CompletableFuture<String> future=new CompletableFuture();
            future.complete("Done");

            System.out.println(future.get());

            future.completeAsync(()->"Done async");

            System.out.println(future.get());

            future.completeAsync(()->"Done async", CompletableFuture.delayedExecutor(10, TimeUnit.SECONDS));

            System.out.println(future.get());

            future.completeOnTimeout("Completed",10,TimeUnit.SECONDS)
                    .thenAccept((data)->System.out.println("completion accepted"+data));

            future.completeOnTimeout("Completed",10,TimeUnit.SECONDS)
                    .thenApply((data)->data.toUpperCase())
                    .thenAccept((data)->System.out.println("completion on timeout"+data));

            future.orTimeout(10,TimeUnit.SECONDS)
                    .whenComplete((data,error)->{
                        if(data!=null){
                            System.out.println("no exception"+data);
                        }
                        else{
                            System.out.println("exception"+error);
                        }
                    });


            future.orTimeout(10,TimeUnit.SECONDS)
                    .whenComplete((data,error)->{
                        if(data!=null){
                            System.out.println("no exception"+data);
                        }
                        else{
                            System.out.println("exception"+error);
                        }
                    });

            CompletableFuture copied=future.copy();
            copied.complete("Cpoied done");

            CompletionStage<String> stage=future.minimalCompletionStage();

            CompletionStage<String> completedStage=future.completedStage("Done");
            completedStage.thenAccept((data)->{System.out.println("completed stage"+data);});

            future=CompletableFuture.failedFuture(new IllegalArgumentException());
            System.out.println("failed future "+future.get());

            completedStage=CompletableFuture.failedStage(new IllegalArgumentException());
            completedStage.thenAccept((data)->{System.out.println("failed stage "+data);});
        }catch(ExecutionException| InterruptedException e){
            e.printStackTrace();
        }

    }
}
