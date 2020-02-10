package cn.suvue.discipline.practice.lock.lockDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * JUC保证原子性
 *
 * @author suvue
 * @date 2020/2/10
 */
public class LockDemo2 {
    private AtomicInteger i= new AtomicInteger(0);

    public void add() {
        i.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo2 demo = new LockDemo2();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        demo.add();
                    }
                }
            }).start();
        }
        Thread.sleep(2000L);
        System.out.println(demo.i);
    }
}
