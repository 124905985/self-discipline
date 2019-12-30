package cn.suvue.discipline.practice.checknull;

import java.util.Objects;

/**
 * 空指针处理
 *
 * @author suvue
 * @date 2019/12/30
 */
public class Checknull {
    public static void main(String[] args){
        String text = null;
        if (Objects.equals(null,"text")){
            System.out.println("text");
        }else {
            System.out.println("不相等");
        }
    }
}
