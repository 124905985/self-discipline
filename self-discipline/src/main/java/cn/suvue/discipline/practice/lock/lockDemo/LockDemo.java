package cn.suvue.discipline.practice.lock.lockDemo;

/**
 * 无法保证原子性
 *
 * @author suvue
 * @date 2020/2/10
 */
public class LockDemo {
    volatile int i = 0;

    public void add() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo demo = new LockDemo();
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
