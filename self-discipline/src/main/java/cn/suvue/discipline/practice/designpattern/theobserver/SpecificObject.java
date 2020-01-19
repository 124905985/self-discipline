package cn.suvue.discipline.practice.designpattern.theobserver;
/**
 * 具体的被观察者类
 *
 * @author suvue
 * @date 2020/1/19
 */
public class SpecificObject extends AbstractObject{
    @Override
    public void dosomething() {
        //这里完成特定的业务
        System.out.println("经过几个小时的奋战，终于又写出了一个bug");
        try {
            //模拟通知观察者的时间消耗
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.notifyObserver();
    }
}
