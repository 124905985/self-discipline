package cn.suvue.discipline.practice.designpattern.builders;

import lombok.Data;

/**
 * 英雄
 *
 * @author suvue
 * @date 2020/1/12
 */
@Data
public class Hero {
    private final HairColor hairColor;
    private final Profession profession;
    private final Weapon weapon;
    private final String name;

    /**
     * 我们传进来一个通用的建造器
     */
    public Hero(Builder builder) {
        this.hairColor = builder.hairColor;
        this.profession = builder.profession;
        this.weapon = builder.weapon;
        this.name = builder.name;
    }

    /**
     * 这是一个静态内部类，我们来实现这个通用建造器
     */
    public static class Builder {
        private HairColor hairColor;
        private final Profession profession;
        private Weapon weapon;
        private final String name;

        /**
         * 建造器的构造函数 我们要求职业和昵称必填
         */
        public Builder(Profession profession, String name) {
            if (profession == null || name == null) {
                throw new IllegalArgumentException("初始化参数为空");
            }
            this.profession = profession;
            this.name = name;
        }

        public Builder withHairColor(HairColor hairColor) {
            this.hairColor = hairColor;
            return this;
        }

        public Builder withWeapon(Weapon weapon) {
            this.weapon = weapon;
            return this;
        }

        public Hero build() {
            return new Hero(this);
        }


    }

}
