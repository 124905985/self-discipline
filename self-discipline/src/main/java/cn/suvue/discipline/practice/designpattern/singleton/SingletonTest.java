package cn.suvue.discipline.practice.designpattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<StaticInnerStyle> constructor = StaticInnerStyle.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        StaticInnerStyle instance = constructor.newInstance();
        //EnumStyle instance = EnumStyle.getInstance();
        System.out.println(instance);
    }
}
