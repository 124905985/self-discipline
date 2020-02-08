package cn.suvue.discipline.practice.demo;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的使用
 *
 * @author suvue
 * @date 2020/2/8
 */
public class Demo9 {
    private void testCommon(ThreadPoolExecutor threadPoolExecutor) throws InterruptedException {
        // 测试： 提交15个执行时间需要3秒的任务，看超过大小的2个，对应的处理情况
        for (int i = 0; i < 15; i++) {
            int n = i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("开始执行：" + n);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println("执行结束：" + n);
                }
            });
            System.out.println("任务提交成功：" + i);
        }
        //查看线程数量，查看线程等待数量
        Thread.sleep(500L);
        System.out.println("当前线程池线程等待数量为："+threadPoolExecutor.getPoolSize());
        System.out.println("当前线程池等待的任务数量为："+threadPoolExecutor.getQueue().size());
        // 等待15秒，查看线程数量和队列数量（理论上，超出核心线程数量的线程会被自动销毁）
        Thread.sleep(15000L);
        System.out.println("当前线程池线程等待数量为："+threadPoolExecutor.getPoolSize());
        System.out.println("当前线程池等待的任务数量为："+threadPoolExecutor.getQueue().size());
    }

    /**
     * 1、线程池信息： 核心线程数量5，最大数量10，无界队列，超出核心线程数量的线程存活时间：5秒
     */
    public void ThreadPoolExecutorTest1() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());
        testCommon(threadPoolExecutor);
    }

    /**
     * 2、 线程池信息： 核心线程数量5，最大数量10，队列大小3，超出核心线程数量的线程存活时间：5秒， 指定拒绝策略的
     */
    public void ThreadPoolExecutorTest2() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(3), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("任务被拒绝执行了！");
            }
        });
        testCommon(threadPoolExecutor);
    }

    public static void main(String[] args) throws InterruptedException {
        //new Demo9().ThreadPoolExecutorTest1();
        new Demo9().ThreadPoolExecutorTest2();
    }


}
