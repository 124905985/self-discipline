package cn.suvue.discipline.practice.designpattern.factorymethod.template;

/**
 * 默认实现工厂类
 * 具体使用中可以根据业务需求，实现不同的工厂类
 *
 * @author suvue
 * @date 2020/1/10
 */
public class DefaultFactory implements IFactory {
    @Override
    public IProduct createProduct() {
        return new DefaultProduct();
    }
}
