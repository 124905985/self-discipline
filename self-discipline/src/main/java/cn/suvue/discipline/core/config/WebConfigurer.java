package cn.suvue.discipline.core.config;

import cn.suvue.discipline.core.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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

    /**
     * 拦截器配置
     *
     * @author suvue
     * @date 2019/12/21 13:43
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/toLogin")
                .excludePathPatterns("/doLogin")
                .excludePathPatterns("/register")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.ico");
    }

    /**
     * 静态资源配置
     *
     * @author suvue
     * @date 2019/12/21 15:44
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classPath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classPath:/templates/");
    }

    /**
     * 跨域配置
     *
     * @author suvue
     * @date 2019/12/21 21:23
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //配置允许跨域的路径
        registry.addMapping("/**")
                //配置允许访问的跨域资源的请求域名
                .allowedOrigins("*")
                //配置允许访问该跨域资源服务器的请求方法，如：POST、GET、PUT、DELETE等
                .allowedMethods("PUT,POST,GET,DELETE,OPTIONS")
                //配置允许请求header的访问，如 ：X-TOKEN
                .allowedHeaders("*");
    }

    /**
     * 视图控制器配置
     *
     * @author suvue
     * @date 2019/12/21 21:27
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/home");
        registry.addViewController("/toLogin").setViewName("/login/login");
    }

    /**
     * 视图解析器配置
     *
     * @author suvue
     * @date 2019/12/21 21:30
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".html");
        viewResolver.setCache(false);
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setOrder(0);
        registry.viewResolver(viewResolver);
    }
}
