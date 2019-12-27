package cn.suvue.discipline.practice.gbyeifelse.strategymethod;

import cn.suvue.discipline.practice.gbyeifelse.RoleOperation;

/**
 * 角色上下文,策略模式
 *
 * @author suvue
 * @date 2019/12/27
 */
public class RoleContext {
    private RoleOperation roleOperation;

    public RoleContext(RoleOperation roleOperation) {
        this.roleOperation = roleOperation;
    }

    public String execute() {
        return roleOperation.op();
    }
}
