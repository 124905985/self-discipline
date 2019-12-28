package cn.suvue.discipline.practice.annotation;

import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 仅仅是一个测试类
 *
 * @author suvue
 * @date 2019/12/28
 */
public class AnnotationTest {
    @MyAnnotation(name = "suvue",age=22)
    public void addUser(String name,Integer age){
        //执行业务逻辑
        //...
        System.out.println("用户添加成功！姓名："+name+" 年龄："+ age);
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //java是通过反射机制使注解生效
        //获取类对象
        Class<AnnotationTest> aClass = AnnotationTest.class;
        //获取到指定方法
        Method aClassMethod = aClass.getMethod("addUser", String.class, Integer.class);
        //获取方法上的指定注解
        MyAnnotation aClassMethodAnnotation = aClassMethod.getAnnotation(MyAnnotation.class);
        //获取注解里的属性和值
        int age = aClassMethodAnnotation.age();
        String name = aClassMethodAnnotation.name();
        //将信息注入到方法上
        AnnotationTest instance = aClass.newInstance();
        aClassMethod.invoke(instance,name,age);
    }
}
