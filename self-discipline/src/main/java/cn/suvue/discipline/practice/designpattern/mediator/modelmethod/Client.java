package cn.suvue.discipline.practice.designpattern.mediator.modelmethod;

public class Client {
    public static void main(String[] args) {
        ColleagueA a = new ColleagueA(1);
        ColleagueB b = new ColleagueB(100);
        Mediator mediator = new Mediator(a, b);
        a.setNumber(2);
        mediator.aAffectB();
        System.out.println(b.getNumber());//200
        b.setNumber(400);
        mediator.bAffectA();
        System.out.println(a.getNumber());//4
    }
}
