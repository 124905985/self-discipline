package cn.suvue.discipline.practice.classloader;

public class ClassLoaderView {
    public static void main(String[] args) throws ClassNotFoundException {
        //核心类库加载器：null
        System.out.println("核心类库加载器："+ClassLoaderView.class.getClassLoader().loadClass("java.lang.String").getClassLoader());
        //拓展类库加载器：sun.misc.Launcher$ExtClassLoader@5a39699c
        System.out.println("拓展类库加载器："+ClassLoaderView.class.getClassLoader().loadClass("com.sun.nio.zipfs.ZipCoder").getClassLoader());
        //应用程序库加载器：sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println("应用程序库加载器："+ClassLoaderView.class.getClassLoader());
        //双亲委派模型
        //应用程序库加载器的父类sun.misc.Launcher$ExtClassLoader@5a39699c
        System.out.println("应用程序库加载器的父类"+ClassLoaderView.class.getClassLoader().getParent());
        //应用程序库加载器的父类的父类null
        System.out.println("应用程序库加载器的父类的父类"+ClassLoaderView.class.getClassLoader().getParent().getParent());
    }
}
