package cn.suvue.discipline.core.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {
    /**
     * 模块名
     */
    private static final String MODULE_NAME = "modular";

    /**
     * 数据库表名(多个表名用逗号隔开)
     */
    private static final String TABLE_NAMES = "sd_articles,sd_users,sd_comments," +
                                                "sd_labels,sd_set_artitle_label," +
                                                "sd_set_artitle_sort,sd_sorts,sd_user_friends";

    /**
     * 渴望移除的表前缀
     */
    private static final String TABLE_PREFIX = "sd_";



    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 项目根路径
        String projectPath = System.getProperty("user.dir");
        //代码生成的文件夹
        gc.setOutputDir(projectPath + "/src/main/java");
        //作者信息
        gc.setAuthor("suvue");
        //代码生成后是否在新的windows文件夹窗口打开
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/self_discipline?useUnicode=true&characterEncoding=UTF-8&useSSL=false" +
                "&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //生成的代码存放的根路径(自动创建包文件夹)
        pc.setModuleName(MODULE_NAME);
        //代码生成位置的包路径(不包含模块的路径)
        pc.setParent("cn.suvue.discipline");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //下划线转驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(TABLE_NAMES.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //移除表前缀
        strategy.setTablePrefix(TABLE_PREFIX);
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
