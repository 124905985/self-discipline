package cn.suvue.discipline.practice.lock.lockDemo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 原子性, 两个线程，对i变量进行递增操作
 *
 * @author suvue
 * @date 2020/2/10
 */
public class LockDemo1 {
    volatile int i = 0;
    /**
     * 直接操作内存，修改对象，数组内存....强大的API
     */
    private static Unsafe unsafe;

    /**
     * 属性的偏移量
     */
    private static Long iOffset;

    static {
        try {
            // 反射技术获取unsafe值
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            //theUnsafe是静态成员变量，null就可以拿到
            unsafe = (Unsafe) field.get(null);
            //利用unsafe,通过属性的偏移量（定位到内存中对象内具体属性的内存地址）
            iOffset = unsafe.objectFieldOffset(LockDemo1.class.getDeclaredField("i"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add() {
        // i++;// JAVA 层面三个步骤
        // CAS + 循环 重试
        int current;
        do {
            //CAS优化
            current = unsafe.getIntVolatile(this, iOffset);
        } while (!unsafe.compareAndSwapInt(this, iOffset, current, current + 1));
        //可能会失败
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo1 demo = new LockDemo1();
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
