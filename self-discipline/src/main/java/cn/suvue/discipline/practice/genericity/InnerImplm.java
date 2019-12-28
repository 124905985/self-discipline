package cn.suvue.discipline.practice.genericity;

/**
 * 明确类型的实现方式
 *
 * @author suvue
 * @date 2019/12/28
 */
public class InnerImplm implements Inner<String> {
    @Override
    public void show(String s) {
        System.out.println(s);
    }
}
