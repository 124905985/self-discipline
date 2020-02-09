package cn.suvue.discipline.practice.demo;

import java.util.List;
import java.util.concurrent.*;

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
    public void threadPoolExecutorTest1() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());
        testCommon(threadPoolExecutor);
    }

    /**
     * 2、 线程池信息： 核心线程数量5，最大数量10，队列大小3，超出核心线程数量的线程存活时间：5秒， 指定拒绝策略的
     */
    public void threadPoolExecutorTest2() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(3), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("任务被拒绝执行了！");
            }
        });
        testCommon(threadPoolExecutor);
    }

    /**
     * 3、 线程池信息： 核心线程数量5，最大数量5，无界队列，超出核心线程数量的线程存活时间：0秒
     */
    public void threadPoolExecutorTest3() throws InterruptedException {
        // 和Executors.newFixedThreadPool(int nThreads)一样的
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        testCommon(threadPoolExecutor);
        // 预计结：线程池线程数量为：5，超出数量的任务，其他的进入队列中等待被执行
    }

    /**
     * 4、 线程池信息：
     * 核心线程数量0，最大数量Integer.MAX_VALUE，SynchronousQueue队列，超出核心线程数量的线程存活时间：60秒
     */
    public void threadPoolExecutorTest4() throws InterruptedException {

        // SynchronousQueue，实际上它不是一个真正的队列，因为它不会为队列中元素维护存储空间。与其他队列不同的是，它维护一组线程，这些线程在等待着把元素加入或移出队列。
        // 在使用SynchronousQueue作为工作队列的前提下，客户端代码向线程池提交任务时，
        // 而线程池中又没有空闲的线程能够从SynchronousQueue队列实例中取一个任务，
        // 那么相应的offer方法调用就会失败（即任务没有被存入工作队列）。
        // 此时，ThreadPoolExecutor会新建一个新的工作者线程用于对这个入队列失败的任务进行处理（假设此时线程池的大小还未达到其最大线程池大小maximumPoolSize）。

        // 和Executors.newCachedThreadPool()一样的
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L,
                TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        testCommon(threadPoolExecutor);
        // 预计结果：
        // 1、 线程池线程数量为：15，超出数量的任务，其他的进入队列中等待被执行
        // 2、 所有任务执行结束，60秒后，如果无任务可执行，所有线程全部被销毁，池的大小恢复为0
        Thread.sleep(60000L);
        System.out.println("60秒后再看线程池中线程的数量："+threadPoolExecutor.getPoolSize());
    }

    /**
     * 5、 定时执行线程池信息：3秒后执行，一次性任务，到点就执行 <br/>
     *核心线程数量5，最大数量Integer.MAX_VALUE，DelayedWorkQueue延时队列，超出核心线程数量的线程存活时间：0秒
     *
     */
    public void threadPoolExecutorTest5(){
        // 和Executors.newScheduledThreadPool()一样的
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        threadPoolExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务被执行，现在时间："+System.currentTimeMillis());
            }
        },3L,TimeUnit.SECONDS);
        System.out.println("定时任务提交成功！时间是："+System.currentTimeMillis()+" 当前线程池中线程数量："+threadPoolExecutor.getPoolSize());
        // 预计结果：任务在3秒后被执行一次
    }

    public void threadPoolExecutorTest6(){
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        // 周期性执行某一个任务，线程池提供了两种调度方式，这里单独演示一下。测试场景一样。
        // 测试场景：提交的任务需要3秒才能执行完毕。看两种不同调度方式的区别
        // 效果1： 提交后，2秒后开始第一次执行，之后每间隔1秒，固定执行一次(如果发现上次执行还未完毕，则等待完毕，完毕后立刻执行)。
        // 也就是说这个代码中是，3秒钟执行一次（计算方式：每次执行三秒，间隔时间1秒，执行结束后马上开始下一次执行，无需等待）
        threadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务-1被执行，当前时间是："+System.currentTimeMillis());
            }
        },2000,1000, TimeUnit.MILLISECONDS);

        // 效果2：提交后，2秒后开始第一次执行，之后每间隔1秒，固定执行一次(如果发现上次执行还未完毕，则等待完毕，等上一次执行完毕后再开始计时，等待1秒)。
        // 也就是说这个代码钟的效果看到的是：4秒执行一次。 （计算方式：每次执行3秒，间隔时间1秒，执行完以后再等待1秒，所以是 3+1）
        threadPoolExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务-2被执行，当前时间是："+System.currentTimeMillis());
            }
        },2000,1000,TimeUnit.MILLISECONDS);
    }

    /**
     * 7、 终止线程：线程池信息： 核心线程数量5，最大数量10，队列大小3，超出核心线程数量的线程存活时间：5秒， 指定拒绝策略的
     *
     * @throws Exception
     */
    private void threadPoolExecutorTest7() throws Exception {
        // 创建一个 核心线程数量为5，最大数量为10,等待队列最大是3 的线程池，也就是最大容纳13个任务。
        // 默认的策略是抛出RejectedExecutionException异常，java.util.concurrent.ThreadPoolExecutor.AbortPolicy
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.err.println("有任务被拒绝执行了");
            }
        });
        // 测试： 提交15个执行时间需要3秒的任务，看超过大小的2个，对应的处理情况
        for (int i = 0; i < 15; i++) {
            int n = i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("开始执行：" + n);
                        Thread.sleep(3000L);
                        System.err.println("执行结束:" + n);
                    } catch (InterruptedException e) {
                        System.out.println("异常：" + e.getMessage());
                    }
                }
            });
            System.out.println("任务提交成功 :" + i);
        }
        // 1秒后终止线程池
        Thread.sleep(1000L);
        threadPoolExecutor.shutdown();
        // 再次提交提示失败
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("追加一个任务");
            }
        });
        // 结果分析
        // 1、 10个任务被执行，3个任务进入队列等待，2个任务被拒绝执行
        // 2、调用shutdown后，不接收新的任务，等待13任务执行结束
        // 3、 追加的任务在线程池关闭后，无法再提交，会被拒绝执行
    }

    /**
     * 8、 立刻终止线程：线程池信息： 核心线程数量5，最大数量10，队列大小3，超出核心线程数量的线程存活时间：5秒， 指定拒绝策略的
     *
     * @throws Exception
     */
    private void threadPoolExecutorTest8() throws Exception {
        // 创建一个 核心线程数量为5，最大数量为10,等待队列最大是3 的线程池，也就是最大容纳13个任务。
        // 默认的策略是抛出RejectedExecutionException异常，java.util.concurrent.ThreadPoolExecutor.AbortPolicy
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.err.println("有任务被拒绝执行了");
            }
        });
        // 测试： 提交15个执行时间需要3秒的任务，看超过大小的2个，对应的处理情况
        for (int i = 0; i < 15; i++) {
            int n = i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("开始执行：" + n);
                        Thread.sleep(3000L);
                        System.err.println("执行结束:" + n);
                    } catch (InterruptedException e) {
                        System.out.println("异常：" + e.getMessage());
                    }
                }
            });
            System.out.println("任务提交成功 :" + i);
        }
        // 1秒后终止线程池
        Thread.sleep(1000L);
        List<Runnable> shutdownNow = threadPoolExecutor.shutdownNow();
        // 再次提交提示失败
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("追加一个任务");
            }
        });
        System.out.println("未结束的任务有：" + shutdownNow.size());

        // 结果分析
        // 1、 10个任务被执行，3个任务进入队列等待，2个任务被拒绝执行
        // 2、调用shutdownnow后，队列中的3个线程不再执行，10个线程被终止
        // 3、 追加的任务在线程池关闭后，无法再提交，会被拒绝执行
    }

    public static void main(String[] args) throws Exception {
        //new Demo9().threadPoolExecutorTest1();
        //new Demo9().threadPoolExecutorTest2();
        //new Demo9().threadPoolExecutorTest3();
        //new Demo9().threadPoolExecutorTest4();
        //new Demo9().threadPoolExecutorTest5();
        //new Demo9().threadPoolExecutorTest6();
        //new Demo9().threadPoolExecutorTest7();
        new Demo9().threadPoolExecutorTest8();
    }


}
