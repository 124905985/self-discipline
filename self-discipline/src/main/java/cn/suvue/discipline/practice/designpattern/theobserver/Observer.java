package cn.suvue.discipline.practice.designpattern.theobserver;

/**
 * 观察者类
 *
 * @author suvue
 * @date 2020/1/19
 */
public interface Observer {
    //接收到改变通知时要进行的操作
    void update();
}
