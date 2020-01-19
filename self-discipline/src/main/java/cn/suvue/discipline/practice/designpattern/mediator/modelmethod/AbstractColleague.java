package cn.suvue.discipline.practice.designpattern.mediator.modelmethod;

/**
 * 同事类的抽象类
 *
 * @author suvue
 * @date 2020/1/18
 */
public abstract class AbstractColleague {
    int number;

    AbstractColleague(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public abstract void setNumber(int number, AbstractMediator mediator);
}
