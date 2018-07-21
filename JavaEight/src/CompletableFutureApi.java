import java.util.concurrent.*;

public class CompletableFutureApi {
    public static void main(String[] args) {
        try {
            CompletableFuture<String> fut = new CompletableFuture<>();
            System.out.println("Thread starting ...");
            new Thread(()->{
                delayThread();
                fut.complete("Done");
            }).start();
            System.out.println("Thread started ...");
            System.out.println(fut.get(10000, TimeUnit.MILLISECONDS));
            System.out.println("Value received (blocking call) ...");

            CompletableFuture.supplyAsync(()-> { delayThread(); return "done";})
                             .thenApply((val)-> val.toUpperCase())
                             .thenAccept(System.out::println);

            CompletableFuture.supplyAsync(RuntimeException::new)
                             .whenComplete((exc, res) -> System.out.println(exc));

            CompletableFuture.supplyAsync(()->{ delayThread(); return "done";},
                              Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), new DaemonThreadFactory()))
                             .thenAccept(System.out::println);

            delayThread();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
    public static void delayThread(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class DaemonThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread th = new Thread(r, "DAEMON - POOL");
        th.setDaemon(true);
        return th;
    }
}
