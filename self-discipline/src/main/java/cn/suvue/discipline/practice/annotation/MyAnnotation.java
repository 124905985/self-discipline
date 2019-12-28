package cn.suvue.discipline.practice.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义一个注解
 *
 * @author suvue
 * @date 2019/12/28
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String name();
    int age();
}
