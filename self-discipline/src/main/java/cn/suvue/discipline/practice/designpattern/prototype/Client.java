package cn.suvue.discipline.practice.designpattern.prototype;

public class Client {
    public static void main(String[] args){
        Sheep tony = new Sheep("Tony");//Tony
        System.out.println(tony.getName());
        //克隆一个出来
        Sheep clone = tony.clone();
        clone.setName("cloneTony");
        System.out.println(clone.getName());//cloneTony
    }
}
