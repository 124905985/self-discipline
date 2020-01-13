package cn.suvue.discipline.practice.designpattern.templateMethod;

/**
 * 播种的抽象类
 *
 * @author suvue
 * @date 2020/1/13
 */
public abstract class AbstractPlantMethod {
    /**
     * 购买种子
     */
    public abstract String buySeeds();

    /**
     * 播撒
     */
    public abstract void sow(String seeds);

    /**
     * 浇水
     */
    public abstract void watering(String seeds);

    /**
     * 种植方法(定义为final,防止子类重写该核心算法)
     */
    public final void planting() {
        String seeds = this.buySeeds();
        this.sow(seeds);
        this.watering(seeds);
    }
}
