package cn.suvue.discipline.practice.designpattern.singleton;
/**
 * 测试单例对象是否会被GC垃圾回收
 *
 * @author suvue
 * @date 2020/1/11
 */
class Singleton {
    //饿汉式单例，bytes数组模拟该单例对象占了100M堆内存
    private static byte[] bytes = new byte[1024 * 1024 * 100];

    private Singleton() {
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

}

class OtherObject {
    //模拟其它一些对象，假设都占用了虚拟机5M堆内存
    private byte[] bytes = new byte[1024 * 1024 * 5];
}


public class SingletonTest {
    public static void main(String[] args) {
        //获取单例对象
        Singleton instance = Singleton.getInstance();
        //死循环创建对象，然后观察虚拟机中堆内存大小的变化
        while (true) {
            new OtherObject();
        }

    }
}
