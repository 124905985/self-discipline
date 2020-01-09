package cn.suvue.discipline.practice.designpattern.singleton;

/**
 * 懒汉式单例(通过静态内部类的方式实现，推荐使用)
 *
 * @author suvue
 * @date 2020/1/9
 */
public class StaticInnerStyle {
    private StaticInnerStyle() {
        if (getInstance()!=null){
            throw new RuntimeException("调用失败");
        }

    }

    private static class InnerInstance {
        private final static StaticInnerStyle INSTANCE = new StaticInnerStyle();
    }

    public static StaticInnerStyle getInstance() {
        return InnerInstance.INSTANCE;
    }
}
