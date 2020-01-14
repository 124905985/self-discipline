package cn.suvue.discipline.practice.designpattern.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 *
 * @author suvue
 * @date 2020/1/14
 */
@Slf4j
public class CglibInterceptor implements MethodInterceptor {
    private static final int NUMBER_ALLOW = 3;
    private int numWizards = 0;


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (numWizards < NUMBER_ALLOW) {
            methodProxy.invokeSuper(o, objects);
            numWizards++;
        } else {
            log.error("象牙塔的进入人数已满，请稍后再来！");
        }
        return null;
    }
}
