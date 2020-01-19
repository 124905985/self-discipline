package cn.suvue.discipline.practice.designpattern.mediator.defaultmethod;

/**
 * 同事类 A
 *
 * @author suvue
 * @date 2020/1/18
 */
public class ColleagueA extends AbstractColleague {

    public ColleagueA(int number) {
        super.setNumber(number);
    }

    @Override
    public void setNumber(int number, AbstractColleague colleague) {
        this.setNumber(number);
        colleague.setNumber(number * 100);
    }
}
