package cn.suvue.discipline.practice.designpattern.factorymethod.example;

/**
 * 发动机
 */
class Engine{

}

/**
 * 轮胎
 */
class Tires{

}

/**
 * 底盘
 */
class Chassis{

}


class Car{
    private Engine engine;
    private Tires tires;
    private Chassis chassis;

    public Car(Engine engine, Tires tires, Chassis chassis) {
        this.engine = engine;
        this.tires = tires;
        this.chassis = chassis;
    }

    public void success(){
        System.out.println("一辆新的小汽车组装完毕！");
    }
}


/**
 * 不使用工厂方法模式造小汽车
 *
 * @author suvue
 * @date 2020/1/11
 */
public class CommonMethod {
    public static void main(String[] args){
        Engine engine = new Engine();
        Tires tires = new Tires();
        Chassis chassis = new Chassis();
        Car car = new Car(engine, tires, chassis);
        car.success();
    }

}
