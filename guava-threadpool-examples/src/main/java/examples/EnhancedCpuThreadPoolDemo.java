package examples;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import examples.util.BigDecimalUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.*;

/**
 * @Author Evan Leung
 **/
public class EnhancedCpuThreadPoolDemo {


    /**
     * CPU逻辑核心数量
     */
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    /**
     * IO阻塞系数
     */
    private static final double BLOCKAGE_COEFFICIENT = 0.9;

    /**
     * 任务队列长度
     */
    private static final int QUEUE_CAPACITY = 10;

    /**
     * 因为任务都不可以丢弃,如果线程池核心线程、任务队列、最大线程都处于满负载状态,由调用者线程直接调用
     */
    private static final RejectedExecutionHandler REJECTED_EXECUTION_HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

    //设置名称
    private static final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("public-cpu-denseness-thead-pool").build();
    private static final ExecutorService pool = new ThreadPoolExecutor(AVAILABLE_PROCESSORS * 2, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(QUEUE_CAPACITY), namedThreadFactory, REJECTED_EXECUTION_HANDLER);

    public static void main(String[] args) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        System.out.println("JVM线程总数:" + bean.getThreadCount());
        //处理业务方法
        long start = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
            final int n = i;
            try {
                Future<Object> future = pool.submit(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {

                        //模拟业务逻辑
                        //Thread.sleep(10);
                        System.out.println("执行第" + n + "个任务");
                        return null;
                    }
                });

                future.get(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
                continue;
            }
            System.out.println("JVM线程总数为:" + bean.getThreadCount());
            long time = System.currentTimeMillis() - start;
            System.out.println(n + "个线程，耗时：" + time);
        }

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("最终JVM线程总数为:" + bean.getThreadCount());

    }


}
