package cn.suvue.discipline.practice.designpattern.templateMethod;

/**
 * 客户端调用者
 *
 * @author suvue
 * @date 2020/1/13
 */
public class Client {
    public static void main(String[] args) {
        Farmers farmers = new Farmers(new WheatMethod());
        //播种小麦
        farmers.plant();
        farmers.changeMethod(new CornMethod());
        //播种玉米
        farmers.plant();
    }
}
