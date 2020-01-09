package cn.suvue.discipline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 系统启动入口
 *
 * @author suvue
 * @date 2019/12/21 10:34
 */
@MapperScan("cn.suvue.discipline.modular.mapper")
@SpringBootApplication
@ServletComponentScan
public class SelfDisciplineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfDisciplineApplication.class, args);
    }

}
