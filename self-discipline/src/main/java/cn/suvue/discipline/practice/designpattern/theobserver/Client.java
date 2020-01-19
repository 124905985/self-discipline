package cn.suvue.discipline.practice.designpattern.theobserver;

public class Client {
    public static void main(String[] args){
        SpecificObject specificObject = new SpecificObject();
        specificObject.addObserver(new ObserverA());
        specificObject.addObserver(new ObserverB());
        specificObject.dosomething();
    }
}
