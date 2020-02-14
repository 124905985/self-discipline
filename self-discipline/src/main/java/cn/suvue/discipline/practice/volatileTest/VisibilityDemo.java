package cn.suvue.discipline.practice.volatileTest;

import java.util.concurrent.TimeUnit;

public class VisibilityDemo {
    private boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        VisibilityDemo demo = new VisibilityDemo();
        Thread thread = new Thread(() -> {
            long i = 0;
            while (demo.flag) {
                i++;
            }
            System.out.println(i);
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        //设置为false，使上面的线程结束while循环
        demo.flag = false;
        System.out.println("被置为false了");
    }
}
