package cn.suvue.discipline.practice.gbyeifelse.factorymethod;

import cn.suvue.discipline.practice.gbyeifelse.RoleOperation;

/**
 * 普通管理员操作类
 *
 * @author suvue
 * @date 2019/12/27
 */
public class NormalAdminRole implements RoleOperation {
    private String roleName;

    public NormalAdminRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String op() {
        return roleName + "has CCC permission";
    }
}
