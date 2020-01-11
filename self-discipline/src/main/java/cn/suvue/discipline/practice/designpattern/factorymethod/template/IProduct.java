package cn.suvue.discipline.practice.designpattern.factorymethod.template;
/**
 * 产品接口
 * 将产品的公有行为抽象出来
 *
 * @author suvue
 * @date 2020/1/10
 */
public interface IProduct {

    //例如每个产品，都有制造方式，我们就可以抽取到接口，让实现类自己去实现它
    void produceMethod();
}
