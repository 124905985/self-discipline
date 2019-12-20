package cn.suvue.discipline.core.config;

import cn.hutool.core.collection.CollUtil;
import cn.suvue.discipline.core.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

/**
 * 实例化登陆的拦截器，并添加规则
 *
 * @author suvue
 * @date 2019/12/20 20:28
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> excludePaths =
                CollUtil.newArrayList("/login", "/register", "/pages/**");
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePaths);
    }
}
