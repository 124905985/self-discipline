package cn.suvue.discipline.practice.designpattern.theobserver;
/**
 * 观察者A
 *
 * @author suvue
 * @date 2020/1/19
 */
public class ObserverA implements Observer{
    @Override
    public void update() {
        System.out.println("观察者A收到消息，要进行处理了...");
    }
}
