import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkAndJoin {
    private static final ForkJoinPool POOL = new ForkJoinPool(2);
    public static void main(String[] args) {
        ComputeSumTasks mainTask = new ComputeSumTasks(new Long[]{1l,2l,3l,4l,5l,6l,7l,8l,9l,10l,11l,12l,13l,14l,15l,16l,17l,18l,19l,20l,21l,22l,23l,24l,25l,26l,27l,28l,29l,30l});
        Long result = POOL.invoke(mainTask);
        System.out.println(result);
        Fibonacci fibo = new Fibonacci(6);
        int number = POOL.invoke(fibo);
        System.out.println(number);
        System.out.println(1<<3);

    }
}
class ComputeSumTasks extends RecursiveTask<Long>{

    private final Long[] data;
    private static final int THRESHOLD = 10;

    ComputeSumTasks(Long[] data){
        this.data = data;
    }
    @Override
    protected Long compute() {
        if(this.data.length > THRESHOLD){
            ForkJoinTask.invokeAll(createSubTasks());
        }
        Long result = Arrays.stream(this.data)
               .mapToLong(Long::valueOf)
               .sum();
        return result;
    }
    private List<ComputeSumTasks> createSubTasks(){
        ComputeSumTasks taskOne = new ComputeSumTasks(Arrays.copyOfRange(this.data, 0, (this.data.length/2)));
        ComputeSumTasks taskTwo = new ComputeSumTasks(Arrays.copyOfRange(this.data, (this.data.length/2), this.data.length));
        List<ComputeSumTasks> tasks = new ArrayList<>();
        tasks.add(taskOne);
        tasks.add(taskTwo);
        return tasks;
    }
}
class Fibonacci extends RecursiveTask<Integer> {
   final int n;
    Fibonacci(int n) { this.n = n; }
    protected Integer compute() {
      if (n <= 1)
        return n;
      Fibonacci f1 = new Fibonacci(n - 1);
      f1.fork();
      Fibonacci f2 = new Fibonacci(n - 2);
      return f2.compute() + f1.join();
    }
  }
