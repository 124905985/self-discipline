package cn.suvue.discipline.practice.designpattern.factorymethod.example;

interface IFactory {
    ICar createBigTruck();
}

interface ICar {
    void success();
}

class BigTruck implements ICar {
    private Engine engine;
    private Tires tires;
    private Chassis chassis;

    public BigTruck(Engine engine, Tires tires, Chassis chassis) {
        this.engine = engine;
        this.tires = tires;
        this.chassis = chassis;
    }

    @Override
    public void success() {
        System.out.println("一辆崭新的大货车组装完毕");
    }
}

class Factory implements IFactory {

    @Override
    public ICar createBigTruck() {
        Engine engine = new Engine();
        Tires tires = new Tires();
        Chassis chassis = new Chassis();
        ICar bigTruck = new BigTruck(engine, tires, chassis);
        return bigTruck;
    }
}

public class Client {
    public static void main(String[] args){
        IFactory factory = new Factory();
        ICar bigTruck = factory.createBigTruck();
        bigTruck.success();
    }

}
