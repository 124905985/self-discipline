package cn.suvue.discipline.practice.designpattern.abstractfactory;

//汽车产品顶级接口
interface ICar {
    void produce();
}

//两厢车
interface CarA extends ICar {

}

//三厢车
interface CarB extends ICar {

}

interface IFactory {
    ICar createCarA1();

    ICar createCarA2();

    ICar createCarB1();

    ICar createCarB2();
}

class CarA1 implements CarA {

    @Override
    public void produce() {
        System.out.println("我是一辆2.0排量的两厢车");
    }
}

class CarA2 implements CarA {

    @Override
    public void produce() {
        System.out.println("我是一辆2.4排量的两厢车");
    }
}

class CarB1 implements CarB {
    @Override
    public void produce() {
        System.out.println("我是一辆2.0排量的三厢车");
    }
}

class CarB2 implements CarB {
    @Override
    public void produce() {
        System.out.println("我是一辆2.4排量的三厢车");
    }
}


class CarFactory implements IFactory {
    @Override
    public ICar createCarA1() {
        return new CarA1();
    }

    @Override
    public ICar createCarA2() {
        return new CarA2();
    }

    @Override
    public ICar createCarB1() {
        return new CarB1();
    }

    @Override
    public ICar createCarB2() {
        return new CarB2();
    }
}

public class Client {
    public static void main(String[] args) {
        IFactory carFactory = new CarFactory();
        carFactory.createCarA1().produce();
        carFactory.createCarA2().produce();
        carFactory.createCarB1().produce();
        carFactory.createCarB2().produce();
    }

}
