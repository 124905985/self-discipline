package cn.suvue.discipline.practice.designpattern.mediator.defaultmethod;
/**
 * 测试类
 *
 * @author suvue
 * @date 2020/1/18
 */
public class Client {
    public static void main(String[] args){
        ColleagueA colleagueA = new ColleagueA(1);
        ColleagueB colleagueB = new ColleagueB(100);
        colleagueA.setNumber(2,colleagueB);//A:2 B:200
        System.out.println(colleagueA.getNumber());
        System.out.println(colleagueB.getNumber());

        colleagueB.setNumber(400,colleagueA);
        System.out.println(colleagueA.getNumber());
        System.out.println(colleagueB.getNumber());//A:4 B:400
    }
}
