package cn.suvue.discipline.practice.designpattern.singleton;

/**
 * 懒汉式单例（双重检索，推荐使用）
 *
 * @author suvue
 * @date 2020/1/9
 */
public class DoubleCheckStyle {
    private static volatile DoubleCheckStyle instance = null;

    private DoubleCheckStyle() {
    }

    public static DoubleCheckStyle getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckStyle.class) {
                if (instance == null) {
                    instance = new DoubleCheckStyle();
                }
            }
        }
        return instance;
    }

}
