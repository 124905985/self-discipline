package cn.suvue.discipline.practice.designpattern.singleton;

/**
 * 饿汉式单例（可以使用）
 *
 * @author suvue
 * @date 2020/1/9
 */
public class HungryStyle {
    private static HungryStyle instance = new HungryStyle();

    private HungryStyle() {
    }

    public static HungryStyle getInstance() {
        return instance;
    }
}
