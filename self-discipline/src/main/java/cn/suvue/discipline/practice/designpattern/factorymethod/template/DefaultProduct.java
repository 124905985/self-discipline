package cn.suvue.discipline.practice.designpattern.factorymethod.template;
/**
 * 产品的默认实现
 *
 * @author suvue
 * @date 2020/1/10
 */
public class DefaultProduct implements IProduct{
    @Override
    public void produceMethod() {
        //此处仅用打印控制台代替具体的实现过程
        System.out.println("我是用传统生产方法造出来的默认产品");
    }
}
