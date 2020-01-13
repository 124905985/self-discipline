package cn.suvue.discipline.practice.designpattern.templateMethod;

/**
 * 小麦作物播种方法
 *
 * @author suvue
 * @date 2020/1/13
 */
public class WheatMethod extends AbstractPlantMethod {
    @Override
    public String buySeeds() {
        System.out.println("购买小麦作物中...");
        return "wheats";
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
