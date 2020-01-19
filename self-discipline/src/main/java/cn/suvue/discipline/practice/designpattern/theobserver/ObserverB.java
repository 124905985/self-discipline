package cn.suvue.discipline.practice.designpattern.theobserver;
/**
 * 观察者B类
 *
 * @author suvue
 * @date 2020/1/19
 */
public class ObserverB implements Observer{
    @Override
    public void update() {
        System.out.println("观察者B收到消息，开始进行处理了...");
    }
}
