package cn.suvue.discipline.practice.lock.sync;
/**
 * 可重入demo
 *
 * @author suvue
 * @date 2020/2/9
 */
public class ObjectSyncDemo2 {
    public synchronized void test(Object arg){
        System.out.println("开始执行："+arg);
        if (arg == null){
            test(new Object());
        }
        System.out.println("执行结束："+arg);
    }

    public static void main(String[] args){
        new ObjectSyncDemo2().test(null);
    }
}
