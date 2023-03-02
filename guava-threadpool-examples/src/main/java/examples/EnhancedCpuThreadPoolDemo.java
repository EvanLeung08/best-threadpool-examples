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



    public static void main(String[] args) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        System.out.println("JVM线程总数:" + bean.getThreadCount());
        //处理业务方法
        long start = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
            final int n = i;
            try {
                Future<Object> future = AbstractCommonThreadPool.CPU_INTENSIVE_THREADPOOL().submit(new Callable<Object>() {
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
