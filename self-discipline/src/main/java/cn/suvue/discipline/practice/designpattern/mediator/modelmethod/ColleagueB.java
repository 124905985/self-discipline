package cn.suvue.discipline.practice.designpattern.mediator.modelmethod;
/**
 * 同事 B
 *
 * @author suvue
 * @date 2020/1/18
 */
public class ColleagueB extends AbstractColleague {

    ColleagueB(int number) {
        super(number);
    }

    @Override
    public void setNumber(int number, AbstractMediator mediator) {
        super.setNumber(number);
        mediator.bAffectA();
    }
}
