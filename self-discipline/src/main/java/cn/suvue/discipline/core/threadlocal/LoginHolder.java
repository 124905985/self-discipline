package cn.suvue.discipline.core.threadlocal;

import cn.suvue.discipline.modular.entity.Users;
import lombok.extern.slf4j.Slf4j;

/**
 * 当前登录人信息存放容器
 *
 * @author suvue
 * @date 2019/12/24 21:33
 */
@Slf4j
public class LoginHolder {
    private static ThreadLocal<Users> threadLocal = new ThreadLocal<>();

    /**
     * 设置用户信息
     */
    public static void setUser(Users user) {
        threadLocal.set(user);
    }

    /**
     * 获取用户登录信息
     */
    public static Users getUser() {
        log.info("当前线程：" + Thread.currentThread().getName());
        return threadLocal.get();
    }

    /**
     * 移除用户信息
     *
     * @author suvue
     * @date 2019/12/24 21:37
     */
    public static void removeUser() {
        threadLocal.remove();
    }
}
