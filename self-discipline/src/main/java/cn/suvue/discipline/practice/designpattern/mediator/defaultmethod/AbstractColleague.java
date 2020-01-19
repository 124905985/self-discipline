package cn.suvue.discipline.practice.designpattern.mediator.defaultmethod;

/**
 * 抽象的同事类
 *
 * @author suvue
 * @date 2020/1/18
 */
public abstract class AbstractColleague {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 更改值的大小
     *
     * @author suvue
     * @date 2020/1/18
     */
    public abstract void setNumber(int number, AbstractColleague colleague);
}
