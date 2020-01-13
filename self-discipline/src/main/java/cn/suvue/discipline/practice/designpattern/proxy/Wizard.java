package cn.suvue.discipline.practice.designpattern.proxy;

/**
 * 巫师
 *
 * @author suvue
 * @date 2020/1/13
 */
public class Wizard {
    private String name;

    public Wizard(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
