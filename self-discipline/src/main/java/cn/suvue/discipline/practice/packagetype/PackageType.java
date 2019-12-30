package cn.suvue.discipline.practice.packagetype;

import java.math.BigDecimal;

/**
 * 包装类型
 *
 * @author suvue
 * @date 2019/12/30
 */
public class PackageType {
    public static void main(String[] args){
        BigDecimal cir = new BigDecimal("3.141592657");
        BigDecimal scale = cir.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(scale); //3.14
    }

    private static void compare() {
        Integer x = 3;
        Integer y = 3;
        System.out.println(x==y); //true
        Integer a = new Integer(5);
        Integer b = new Integer(5);
        System.out.println(a==b);//false
        System.out.println(a.equals(b)); //true
    }
}
