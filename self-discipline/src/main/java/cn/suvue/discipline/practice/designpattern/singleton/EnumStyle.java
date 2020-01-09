package cn.suvue.discipline.practice.designpattern.singleton;

/**
 * 枚举方式实现的单例
 *
 * @author suvue
 * @date 2020/1/9
 */
public class EnumStyle {
    private EnumStyle() {
    }

    private enum Singleton {
        /**
         * 单例
         */
        INSTANCE;
        private final EnumStyle instance;

        Singleton() {
            this.instance = new EnumStyle();
        }

        private EnumStyle getInstance() {
            return instance;
        }
    }

    public static EnumStyle getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}
