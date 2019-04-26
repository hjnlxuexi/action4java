package hj.action.concurrent.ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * <p>Title : 计算累加</p>
 * <p>Description : </p>
 * <p>Date : 2019-04-11 </p>
 *
 * @author : hejie
 */
public class CountTask extends RecursiveTask<Integer> {
    //阈值，最小运算单元是两个操作数
    private final static int THRESHOLD = 2;

    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 执行计算
     *
     * @return 计算结果
     */
    @Override
    protected Integer compute() {
        int sum = 0;

        if ((end-start)<=THRESHOLD){
            //for (int i=start;i<=end;i++){
            //    sum += i;
            //}
            int i = start;
            while (i<=end){
                sum += i++;
            }
        } else {
            int middle = (end+start)/2;
            CountTask lowerTask = new CountTask(start, middle);
            CountTask higherTask = new CountTask(middle+1, end);

            // Fork 分割任务
            lowerTask.fork();
            higherTask.fork();

            // Join 合并计算
            int lowerResult = lowerTask.join();
            int higherResult = higherTask.join();

            // 合并最终结果
            sum = lowerResult + higherResult;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountTask task = new CountTask(1,100);
        ForkJoinPool pool = new ForkJoinPool();
        Future<Integer> result =  pool.submit(task);

        System.out.println(result.get());

    }
}
