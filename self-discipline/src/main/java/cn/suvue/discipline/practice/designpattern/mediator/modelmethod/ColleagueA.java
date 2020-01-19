package cn.suvue.discipline.practice.designpattern.mediator.modelmethod;

/**
 * 同事 A
 *
 * @author suvue
 * @date 2020/1/18
 */
public class ColleagueA extends AbstractColleague {

    public ColleagueA(int number) {
        super(number);
    }

    @Override
    public void setNumber(int number, AbstractMediator mediator) {
        super.setNumber(number);
        mediator.aAffectB();
    }
}
