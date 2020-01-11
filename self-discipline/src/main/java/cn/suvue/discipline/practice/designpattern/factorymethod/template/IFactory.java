package cn.suvue.discipline.practice.designpattern.factorymethod.template;

/**
 * 工厂接口
 * 将工厂的公有行为抽象出来
 *
 * @author suvue
 * @date 2020/1/10
 */
public interface IFactory {
    /**
     * 抽象一些工厂行为
     */
    IProduct createProduct();
}
