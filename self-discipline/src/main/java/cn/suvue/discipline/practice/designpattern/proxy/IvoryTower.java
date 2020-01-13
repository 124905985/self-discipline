package cn.suvue.discipline.practice.designpattern.proxy;

import lombok.extern.slf4j.Slf4j;
/**
 * 象牙塔(所有人都能进入，没有人数限制)
 *
 * @author suvue
 * @date 2020/1/13
 */
@Slf4j
public class IvoryTower implements Tower {
    @Override
    public void enter(Wizard wizard) {
        log.info("{}进入了象牙塔", wizard.toString());
    }
}
