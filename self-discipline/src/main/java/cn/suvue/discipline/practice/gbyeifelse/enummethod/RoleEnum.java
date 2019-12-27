package cn.suvue.discipline.practice.gbyeifelse.enummethod;

import cn.suvue.discipline.practice.gbyeifelse.RoleOperation;

/**
 * 定义不同角色做不同操作的枚举类
 *
 * @author suvue
 * @date 2019/12/27
 */
public enum RoleEnum implements RoleOperation {
    /**
     * 系统管理员的操作
     */
    ROLE_ROOT_ADMIN{
        @Override
        public String op() {
            return "ROLE_ROOT_ADMIN"+"has AAA permission";
        }
    },

    /**
     * 订单管理员的操作
     */
    ROLE_ORDER_ADMIN{
        @Override
        public String op() {
            return "ROLE_ORDER_ADMIN"+"has BBB permission";
        }
    },

    /**
     * 普通用户的操作
     */
    ROLE_NORMAL{
        @Override
        public String op() {
            return "ROLE_NORMAL"+"has CCC permission";
        }
    }

    ;

}
