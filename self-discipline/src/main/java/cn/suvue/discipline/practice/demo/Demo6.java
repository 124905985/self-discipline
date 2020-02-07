package cn.suvue.discipline.practice.demo;

import java.util.concurrent.locks.LockSupport;

/**
 * 三种线程协作通信的方式：suspend/resume、wait/notify、park/unpark
 *
 * @author suvue
 * @date 2020/2/7
 */
public class Demo6 {
    public static Object baozidian = null;

    /**
     * 正常的suspend/resume
     */
    public void suspendResumeTest() throws InterruptedException {
        Thread consumeThread = new Thread(() -> {
            while (baozidian == null) {
                System.out.println("进入等待");
                Thread.currentThread().suspend();
            }
            System.out.println("买到包子，准备回家");
        });
        consumeThread.start();
        //3秒钟之后 生产一个包子
        Thread.sleep(3000);
        baozidian = new Object();
        consumeThread.resume();
        System.out.println("通知消费者");

    }

    /**
     * 死锁的suspend/resume。 suspend并不会像wait一样释放锁，故此容易写出死锁代码
     */
    public void suspendResumeDeadLockTest() throws InterruptedException {
        Thread consumeThread = new Thread(() -> {
            while (baozidian == null) {
                System.out.println("进入等待");
                synchronized (this) {
                    Thread.currentThread().suspend();
                }
            }
            System.out.println("买到包子，回家");
        });
        consumeThread.start();
        //3秒之后生产包子
        Thread.sleep(3000);
        baozidian = new Object();
        //争取到锁之后，再恢复consumeThread
        synchronized (this) {
            consumeThread.resume();
        }
        System.out.println("通知消费者");
    }

    /**
     * 导致程序永久挂起的suspend/resume
     */
    public void suspendResumeDeadLockTest2() throws InterruptedException {
        Thread consumeThread = new Thread(() -> {
            while (baozidian == null) {
                System.out.println("进入等待");
                try {//这里模拟一点延迟
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.currentThread().suspend();
            }
            System.out.println("买到包子，回家");
        });
        consumeThread.start();
        //等待3秒，生产包子
        Thread.sleep(3000);
        baozidian = new Object();
        consumeThread.resume();
        System.out.println("通知消费者");
    }

    /**
     * 正常的waitNotify
     */
    public void waitNotifyTest() throws InterruptedException {
        Thread consumeThread = new Thread(() -> {
            while (baozidian == null) {
                synchronized (this) {
                    try {
                        System.out.println("进入等待");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("买到包子，回家");
        });
        consumeThread.start();
        Thread.sleep(3000);
        baozidian = new Object();
        synchronized (this) {
            this.notifyAll();
            System.out.println("通知消费者");
        }
    }

    /**
     * 会导致程序永久等待的wait/notify
     */
    public void waitNotifyDeadLockTest() throws InterruptedException {
        Thread consumeThread = new Thread(() -> {
            while (baozidian == null) {
                try {//模拟延迟
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    try {
                        System.out.println("进入等待");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("买到包子，回家");
            }
        });
        consumeThread.start();
        //3秒之后，生产包子
        Thread.sleep(3000);
        baozidian = new Object();
        synchronized (this){
            this.notifyAll();
        }
        System.out.println("通知消费者");
    }

    /**
     * 正常的park/unpark
     */
    public void parkUnParkTest() throws InterruptedException {
        Thread consumeThread = new Thread(() -> {
            while (baozidian == null) {
                try { //模拟延迟，来调换park/unpark的调用顺序
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("进入等待");
                LockSupport.park();
            }
            System.out.println("买到包子，回家");
        });
        consumeThread.start();
        //3秒之后生产包子
        Thread.sleep(3000);
        baozidian = new Object();
        LockSupport.unpark(consumeThread);
        System.out.println("通知消费者");
    }

    /**
     * 死锁的park/unpark
     */
    public void parkUnParkDeadLockTest() throws InterruptedException {
        Thread consumeThread = new Thread(() -> {
            while (baozidian == null) {
                synchronized (this){
                    System.out.println("进入等待");
                    //注意这里挂起之后不会释放对象锁
                    LockSupport.park();
                }
            }
            System.out.println("买到包子，回家");
        });
        consumeThread.start();
        //3秒之后生产包子
        Thread.sleep(3000);
        baozidian = new Object();
        synchronized (this){
            LockSupport.unpark(consumeThread);
        }
        System.out.println("通知消费者");
    }


    public static void main(String[] args) throws InterruptedException {
        // 对调用顺序有要求，也要开发自己注意锁的释放。这个被弃用的API， 容易死锁，也容易导致永久挂起。
        //new Demo6().suspendResumeTest();
        //new Demo6().suspendResumeDeadLockTest();
        //new Demo6().suspendResumeDeadLockTest2();

        //wait/notify要求再同步关键字里面使用，免去了死锁的困扰，但是一定要先调用wait，再调用notify，否则永久等待了
        //new Demo6().waitNotifyTest();
        //new Demo6().waitNotifyDeadLockTest();

        // park/unpark没有顺序要求，但是park并不会释放锁，所有再同步代码中使用要注意
        new Demo6().parkUnParkTest();
        //new Demo6().parkUnParkDeadLockTest();
    }
}
