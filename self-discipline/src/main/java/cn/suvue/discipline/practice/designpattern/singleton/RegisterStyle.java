package cn.suvue.discipline.practice.designpattern.singleton;


import java.util.concurrent.ConcurrentHashMap;
/**
 * 注册表实现的单例
 *
 * @author suvue
 * @date 2020/1/9
 */
public class RegisterStyle {
    private static ConcurrentHashMap<String, Object> register = new ConcurrentHashMap<String, Object>(32);

    static {
        RegisterStyle res = new RegisterStyle();
        register.put(res.getClass().getName(), res);
    }

    private RegisterStyle() {
    }

    public static RegisterStyle getInstance(String name) {
        if (name == null) {
            name = "cn.suvue.discipline.practice.designpattern.singleton.RegisterStyle";
        }
        if (register.get(name) == null) {
            try {
                register.put(name, Class.forName(name).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (RegisterStyle) register.get(name);
    }
}
