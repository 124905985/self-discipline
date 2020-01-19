package cn.suvue.discipline.practice.designpattern.theobserver;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 被观察者的抽象类
 *
 * @author suvue
 * @date 2020/1/19
 */
public abstract class AbstractObject {

    private CopyOnWriteArrayList<Observer> observerContainer = new CopyOnWriteArrayList<Observer>();

    public void addObserver(Observer observer) {
        this.observerContainer.add(observer);
    }

    public void deleteObserver(Observer observer) {
        this.observerContainer.remove(observer);
    }

    public void notifyObserver() {
        for (Observer o : observerContainer) {
            o.update();
        }
    }

    /**
     * 被监听者需要完成的业务操作
     */
    public abstract void dosomething();
}
