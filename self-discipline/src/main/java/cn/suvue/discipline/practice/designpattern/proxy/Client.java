package cn.suvue.discipline.practice.designpattern.proxy;
/**
 * 客户端调用者
 *
 * @author suvue
 * @date 2020/1/13
 */
public class Client {
    public static void main(String[] args){
        IvoryTowerProxy towerProxy = new IvoryTowerProxy(new IvoryTower());
        towerProxy.enter(new Wizard("红巫师"));
        towerProxy.enter(new Wizard("黄巫师"));
        towerProxy.enter(new Wizard("蓝巫师"));
        towerProxy.enter(new Wizard("绿巫师"));
        towerProxy.enter(new Wizard("白巫师"));
    }
}
