package cn.suvue.discipline.practice.exception;

public class FinallyTest {
    public static void main(String[] args){
        int value = 3;
        System.out.println(method(value));//输出0
    }

    private static int method(int value) {
        try {
            return value*value;
        }finally {
            return 0;
        }
    }
}
