package cn.suvue.discipline.practice.gbyeifelse.factorymethod;

import cn.suvue.discipline.practice.gbyeifelse.RoleOperation;

/**
 * 订单管理员操作类
 *
 * @author suvue
 * @date 2019/12/27
 */
public class OrderAdminRole implements RoleOperation {
    private String roleName;

    public OrderAdminRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String op() {
        return roleName + "has BBB permission";
    }
}
