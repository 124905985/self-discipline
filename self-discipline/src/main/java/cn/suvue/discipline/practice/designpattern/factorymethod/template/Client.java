package cn.suvue.discipline.practice.designpattern.factorymethod.template;

/**
 * 客户端调用者
 *
 * @author suvue
 * @date 2020/1/10
 */
public class Client {
    public static void main(String[] args) {
        IFactory factory = new DefaultFactory();
        IProduct product = factory.createProduct();
        product.produceMethod();
    }
}
