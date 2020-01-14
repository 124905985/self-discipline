package cn.suvue.discipline.practice.designpattern.proxy.cglib;

import cn.suvue.discipline.practice.designpattern.proxy.IvoryTower;
import cn.suvue.discipline.practice.designpattern.proxy.Tower;
import cn.suvue.discipline.practice.designpattern.proxy.Wizard;
import org.springframework.cglib.proxy.Enhancer;

public class Client {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(IvoryTower.class);
        enhancer.setCallback(new CglibInterceptor());
        Tower tower = (Tower) enhancer.create();
        tower.enter(new Wizard("白巫师"));
        tower.enter(new Wizard("黑巫师"));
        tower.enter(new Wizard("红巫师"));
        tower.enter(new Wizard("蓝巫师"));
        tower.enter(new Wizard("绿巫师"));
    }
}
