package cn.suvue.discipline.practice.designpattern.mediator.modelmethod;

/**
 * 抽象的中介类
 *
 * @author suvue
 * @date 2020/1/18
 */
public abstract class AbstractMediator {
    AbstractColleague A;
    AbstractColleague B;

    AbstractMediator(AbstractColleague a, AbstractColleague b) {
        this.A = a;
        this.B = b;
    }

    public abstract void aAffectB();

    public abstract void bAffectA();

}
