package cn.suvue.discipline.practice.designpattern.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 我是象牙塔的代理，最多只允许3个人进入
 *
 * @author suvue
 * @date 2020/1/13
 */
@Slf4j
public class IvoryTowerProxy implements Tower {
    private static final int NUMBER_ALLOW = 3;
    private Tower tower;
    private int numWizards;

    public IvoryTowerProxy(Tower tower) {
        this.tower = tower;
    }

    @Override
    public void enter(Wizard wizard) {
        if (numWizards < NUMBER_ALLOW) {
            tower.enter(wizard);
            numWizards++;
        } else {
            log.error("象牙塔的进入人数已满，请稍后再来！");
        }
    }
}
