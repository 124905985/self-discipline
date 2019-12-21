package cn.suvue.discipline.core.entity;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * spring上下文容器
 *
 * @author suvue
 * @date 2019/12/21 15:29
 */
public class SpringContextContainer implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextContainer.applicationContext = applicationContext;
    }

    /**
     * 获取spring上下文容器
     *
     * @author suvue
     * @date 2019/12/21 15:28
     */
    public static ApplicationContext getApplicationContext() {
        checkedContainer();
        return applicationContext;
    }

    /**
     * 根据beanName获取bean
     *
     * @author suvue
     * @date 2019/12/21 15:28
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        checkedContainer();
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 根据beanClassType获取bean
     *
     * @author suvue
     * @date 2019/12/21 15:28
     */
    public static <T> T getBean(Class<T> requiredType) {
        checkedContainer();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 检查spring容器是否初始化
     *
     * @author suvue
     * @date 2019/12/21 15:26
     */
    public static void checkedContainer() {
        if (SpringContextContainer.applicationContext == null) {
            throw new RuntimeException("获取失败,请检查将所需的bean注入spring容器！");
        }
    }
}
