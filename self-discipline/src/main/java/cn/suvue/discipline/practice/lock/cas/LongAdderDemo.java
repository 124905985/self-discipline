package cn.suvue.discipline.practice.lock.cas;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 测试两秒中内，三种方式的执行次数
 *
 * @author suvue
 * @date 2020/2/10
 */
public class LongAdderDemo {
    private int count = 0;

    public void testSync() {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < 2000) {
                    synchronized (this){
                        ++count;
                    }
                }
                long endTime = System.currentTimeMillis();
                System.out.println("SyncThread spend："+(endTime - startTime)+" ms"+" v:"+count);
            }).start();
        }
    }

    private AtomicLong account = new AtomicLong(0);
    public void testAtomic() {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < 2000) {
                    account.incrementAndGet();
                }
                long endTime = System.currentTimeMillis();
                System.out.println("AtomicThread spend："+(endTime - startTime)+" ms"+" v:"+account);
            }).start();
        }
    }

    private LongAdder lcount = new LongAdder();
    public void testLongAdder() {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < 2000) {
                    lcount.increment();
                }
                long endTime = System.currentTimeMillis();
                System.out.println("longAdderThread spend："+(endTime - startTime)+" ms"+" v:"+lcount);
            }).start();
        }
    }

    public static void main(String[] args){
        LongAdderDemo demo = new LongAdderDemo();
        demo.testSync();
        demo.testAtomic();
        demo.testLongAdder();
    }
}
