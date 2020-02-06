package cn.suvue.discipline.practice.demo;
/**
 * 演示线程stop方法的错误用法
 *
 * 输出结果为i=1 j=0  没能够保证数据一致性
 * @author suvue
 * @date 2020/2/6
 */
class StopThread extends Thread{
    private int i,j;

    @Override
    public void run() {
        synchronized (this){
            ++i;
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ++j;
        }
    }
    public void print(){
        System.out.println("i="+i+" j="+j);
    }
}
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        StopThread thread = new StopThread();
        thread.start();
        Thread.sleep(2000);
        thread.stop();
        while (thread.isAlive()){

        }
        thread.print();
    }
}
