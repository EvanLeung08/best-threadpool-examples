package examples;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import examples.util.BigDecimalUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.BiConsumer;

/**
 * @Author Evan Leung
 **/
public class EnhancedIoThreadPoolDemo {


    public static void main(String[] args) {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        System.out.println("JVM线程总数:" + bean.getThreadCount());
        //处理业务方法
        long start = System.currentTimeMillis();

        Map<Future<String>, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            final int n = i;
            Future<String> future = AbstractCommonThreadPool.IO_INTENSIVE_THREADPOOL().submit(() -> {
                String flag = "SUCCESS";
                try {
                    //模拟业务逻辑
                    System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId() + "开始执行第" + n + "个任务,线程总数为:" + bean.getThreadCount());
                    Thread.sleep(100000);
                    System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId() + "完成执行第" + n + "个任务,线程总数为:" + bean.getThreadCount());
                } catch (InterruptedException ex) {
                    System.out.println("接收到中断信号，正在退出程序！");
                    flag = "FALSE";
                }
                //退出程序
                return flag;
            });
            AbstractCommonThreadPool.MONITOR_THREADPOOL().submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("开始检查超时线程:");
                        String s = future.get(10, TimeUnit.SECONDS);
                        System.out.println("执行结果" + s);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    } catch (ExecutionException e) {
                        System.out.println(e.getMessage());
                    } catch (TimeoutException e) {
                        //通知线程终止执行任务
                        future.cancel(true);
                        System.out.println("当前任务:" + n + "超时，是否已经停止？" + future.isCancelled());
                        System.out.println("最终JVM线程总数为:" + bean.getThreadCount());
                    }
                }
            });
            System.out.println("JVM线程总数为:" + bean.getThreadCount());
            long time = System.currentTimeMillis() - start;
            System.out.println(n + "个线程，耗时：" + time);
        }
        //检测超时线程，然后剔除
    }


}
