package cn.suvue.discipline.practice.designpattern.prototype;

import lombok.Data;

@Data
public class Sheep implements Cloneable {
    private String name;

    public Sheep(String name) {
        this.name = name;
    }

    @Override
    public Sheep clone() {
        try {
            return (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
}
