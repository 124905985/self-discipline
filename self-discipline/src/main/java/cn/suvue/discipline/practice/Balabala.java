package cn.suvue.discipline.practice;

import lombok.Data;

@Data
class Suvue {
    private String name;

    public Suvue(String name) {
        this.name = name;
    }
}

public class Balabala {
    public static void main(String[] args) {
        String s1=new String("suvue");
        String s2=new String("suvue");
        System.out.println(s1.equals(s2));//true
    }
}
