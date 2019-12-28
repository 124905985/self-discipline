package cn.suvue.discipline.practice.genericity;

/**
 * 不明确类型的实现方式
 *
 * @author suvue
 * @date 2019/12/28
 */
public class InnerImpl<T> implements Inner<T> {
    @Override
    public void show(T t) {
        System.out.println(t);
    }
}
