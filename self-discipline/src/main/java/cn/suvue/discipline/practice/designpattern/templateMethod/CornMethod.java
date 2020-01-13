package cn.suvue.discipline.practice.designpattern.templateMethod;

/**
 * 玉米作物播种方法
 *
 * @author suvue
 * @date 2020/1/13
 */
public class CornMethod extends AbstractPlantMethod {
    @Override
    public String buySeeds() {
        System.out.println("购买玉米作物中...");
        return "corns";
    }

    @Override
    public void sow(String seeds) {
        System.out.println("正在播撒" + seeds + "中...");
    }

    @Override
    public void watering(String seeds) {
        System.out.println("正在给" + seeds + "浇水中...");
    }
}
