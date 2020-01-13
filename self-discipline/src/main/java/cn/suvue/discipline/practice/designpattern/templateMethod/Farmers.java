package cn.suvue.discipline.practice.designpattern.templateMethod;

/**
 * 农民伯伯类
 *
 * @author suvue
 * @date 2020/1/13
 */
public class Farmers {
    private AbstractPlantMethod plantMethod;

    public Farmers(AbstractPlantMethod plantMethod) {
        this.plantMethod = plantMethod;
    }

    public void plant() {
        plantMethod.planting();
    }

    public void changeMethod(AbstractPlantMethod plantMethod) {
        this.plantMethod = plantMethod;
    }

}
