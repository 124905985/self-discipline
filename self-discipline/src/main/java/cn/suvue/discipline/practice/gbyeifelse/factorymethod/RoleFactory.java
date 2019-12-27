package cn.suvue.discipline.practice.gbyeifelse.factorymethod;

import cn.suvue.discipline.practice.gbyeifelse.RoleOperation;

import java.util.HashMap;

/**
 * 角色的工厂类
 *
 * @author suvue
 * @date 2019/12/27
 */
public class RoleFactory {
    private static HashMap<String, RoleOperation> roleOperationMap = new HashMap();

    static {
        roleOperationMap.put("ROLE_ROOT_ADMIN", new RootAdminRole("ROLE_ROOT_ADMIN"));
        roleOperationMap.put("ROLE_ORDER_ADMIN", new OrderAdminRole("ROLE_ORDER_ADMIN"));
        roleOperationMap.put("ROLE_NORMAL", new NormalAdminRole("ROLE_NORMAL"));
    }

    public static RoleOperation getOp(String roleName) {
        return roleOperationMap.get(roleName);
    }
}
