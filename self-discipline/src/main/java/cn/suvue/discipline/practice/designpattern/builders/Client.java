package cn.suvue.discipline.practice.designpattern.builders;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;

public class Client {
    public static void main(String[] args) {
        //创建一个拿着法杖的红发魔法师角色
        Hero mage = new Hero
                .Builder(Profession.MAGE, "法师")
                .withHairColor(HairColor.RED)
                .withWeapon(Weapon.ROD)
                .build();
        System.out.println(mage.toString());

        //创建一个拿着剑的黑发战士角色
        Hero warrior = new Hero.Builder(Profession.WARRIOR, "战士")
                .withHairColor(HairColor.BLACK)
                .withWeapon(Weapon.SWORD)
                .build();
        System.out.println(warrior.toString());



    }
}
