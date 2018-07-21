import java.util.concurrent.CompletableFuture;

public class CompletableFutureApi {
    public static void main(String[] args) {
        CompletableFuture<String> fut = new CompletableFuture<>();
        System.out.println("Thread starting ...");
        new Thread(()->{
            delayThread();
            fut.complete("Done");
        }).start();
        System.out.println("Thread started ...");
    }
    public static final void delayThread(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
