package cn.suvue.discipline.practice.designpattern.proxy.jdk;

import cn.suvue.discipline.practice.designpattern.proxy.IvoryTower;
import cn.suvue.discipline.practice.designpattern.proxy.Tower;
import cn.suvue.discipline.practice.designpattern.proxy.Wizard;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args){
        Tower target = new IvoryTower();
        Tower proxyInstance = (Tower)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new JdkProxyHandler(target));
        proxyInstance.enter(new Wizard("白巫师"));
        proxyInstance.enter(new Wizard("黑巫师"));
        proxyInstance.enter(new Wizard("红巫师"));
        proxyInstance.enter(new Wizard("蓝巫师"));
        proxyInstance.enter(new Wizard("绿巫师"));
    }
}
