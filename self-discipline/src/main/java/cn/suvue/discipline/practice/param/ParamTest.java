package cn.suvue.discipline.practice.param;

public class ParamTest {
    //main是主调函数
    public static void main(String[] args){
        //"我的名字叫suvue"是实际参数
        printIn("我的名字叫suvue");
    }

    //printIn是被调函数
    //String类型的value是形式参数
    private static void printIn(String value) {
        System.out.println(value);
    }
}
