package examples;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import examples.util.BigDecimalUtils;

import java.util.concurrent.*;

/**
 * @Author Evan Leung
 **/
public class AbstractCommonThreadPool {


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

    private static final ExecutorService CPU_INTENSIVE_THREADPOOL = new ThreadPoolExecutor(AVAILABLE_PROCESSORS * 2, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(QUEUE_CAPACITY), new ThreadFactoryBuilder()
            .setNameFormat("public-cpu-denseness-thead-pool").build(), REJECTED_EXECUTION_HANDLER);


    private static final ExecutorService IO_INTENSIVE_THREADPOOL = new ThreadPoolExecutor((int) BigDecimalUtils.divideEx((double) AVAILABLE_PROCESSORS, BigDecimalUtils.subtract(1, BLOCKAGE_COEFFICIENT), BigDecimalUtils.ROUND_UP), 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(QUEUE_CAPACITY), new ThreadFactoryBuilder()
            .setNameFormat("public-io-denseness-thead-pool").build(), REJECTED_EXECUTION_HANDLER);

    private static final ExecutorService MONITOR_THREADPOOL = new ThreadPoolExecutor(0, 300,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(QUEUE_CAPACITY), new ThreadFactoryBuilder()
            .setNameFormat("public-monitor-thead-pool").build(), REJECTED_EXECUTION_HANDLER);


    public static ExecutorService IO_INTENSIVE_THREADPOOL() {
        return IO_INTENSIVE_THREADPOOL;
    }


    public static ExecutorService CPU_INTENSIVE_THREADPOOL() {
        return CPU_INTENSIVE_THREADPOOL;
    }

    public static ExecutorService MONITOR_THREADPOOL() {
        return MONITOR_THREADPOOL;
    }



}
