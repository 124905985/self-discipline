package cn.suvue.discipline.practice.gbyeifelse;

import cn.suvue.discipline.practice.gbyeifelse.enummethod.RoleEnum;
import cn.suvue.discipline.practice.gbyeifelse.factorymethod.RoleFactory;
import cn.suvue.discipline.practice.gbyeifelse.factorymethod.RootAdminRole;
import cn.suvue.discipline.practice.gbyeifelse.strategymethod.RoleContext;

/**
 * 根据角色名判断有哪些权限
 *
 * @author suvue
 * @date 2019/12/27
 */
public class JudgeRole {

    /**
     * 繁杂的if else
     */
    public String judge(String roleName) {
        String result;
        if ("ROLE_ROOT_ADMIN".equals(roleName)) {
            //系统管理员有A权限
            result = "ROLE_ROOT_ADMIN" + "has AAA permission";
        } else if ("ROLE_ORDER_ADMIN".equals(roleName)) {
            //订单管理员拥有B权限
            result = "ROLE_ORDER_ADMIN" + "has BBB permission";
        } else if ("ROLE_NORMAL".equals(roleName)) {
            //普通用户拥有C权限
            result = "ROLE_NORMAL" + "has CCC permisson";
        } else {
            result = "xxx";
        }
        return result;

    }

    /**
     * 枚举方式
     */
    public String judgeByEnum(String roleName) {
        //枚举类方式 一行代码搞定
        return RoleEnum.valueOf(roleName).op();
    }

    /**
     * 工厂模式
     */
    public String judgeByFactory(String roleName) {
        //一行代码搞定
        return RoleFactory.getOp(roleName).op();
    }

    /**
     * 策略模式
     */
    public String judgeByStrategy(String roleName) {
        RoleContext roleContext = new RoleContext(new RootAdminRole("ROOT_ADMIN_ROLE"));
        return roleContext.execute();
    }

    public static void main(String[] args){
        JudgeRole judgeRole = new JudgeRole();
        String byEnum = judgeRole.judgeByEnum("ROLE_ROOT_ADMIN");
        System.out.println(byEnum);
    }
}
