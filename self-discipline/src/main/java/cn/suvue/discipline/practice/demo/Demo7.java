package cn.suvue.discipline.practice.demo;
/**
 *演示线程封闭
 *
 * @author suvue
 * @date 2020/2/7
 */
public class Demo7 {
    private static ThreadLocal<String> values = new ThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {
        values.set("主线程存放的数据");
        System.out.println("线程-1启动之前主线程："+values.get());
        new Thread(() -> {
            values.set("线程-1存放的数据");
            System.out.println("线程-1："+values.get());
        }).start();
        Thread.sleep(3000);
        System.out.println("线程-1启动之后主线程："+values.get());
    }
}
