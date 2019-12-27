package cn.suvue.discipline.practice.gbyeifelse.factorymethod;

import cn.suvue.discipline.practice.gbyeifelse.RoleOperation;

/**
 * 系统管理员操作类
 *
 * @author suvue
 * @date 2019/12/27
 */
public class RootAdminRole implements RoleOperation {
    private String roleName;

    public RootAdminRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String op() {
        return roleName + "has AAA permission";
    }
}
