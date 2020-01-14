package cn.suvue.discipline.practice.designpattern.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理类
 *
 * @author suvue
 * @date 2020/1/14
 */
@Slf4j
public class JdkProxyHandler implements InvocationHandler {

    private static final int NUMBER_ALLOW = 3;
    private int numWizards = 0;
    private Object target;

    public JdkProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (numWizards < NUMBER_ALLOW) {
            method.invoke(target, args);
            numWizards++;
        } else {
            log.error("象牙塔的进入人数已满，请稍后再来！");
        }
        return null;
    }
}
