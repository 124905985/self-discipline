package cn.suvue.discipline.practice.designpattern.mediator.modelmethod;
/**
 * 中介者实现类
 *
 * @author suvue
 * @date 2020/1/18
 */
public class Mediator extends AbstractMediator {

    public Mediator(AbstractColleague a, AbstractColleague b) {
        super(a, b);
    }

    @Override
    public void aAffectB() {
        B.setNumber(A.getNumber() * 100);
    }

    @Override
    public void bAffectA() {
        A.setNumber(B.getNumber() / 100);
    }
}
