package cn.suvue.discipline.core.interceptor;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.suvue.discipline.core.consts.SysConst;
import cn.suvue.discipline.core.tools.HttpTool;
import cn.suvue.discipline.modular.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 自定义登录拦截器
 *
 * @author suvue
 * @date 2019/12/20 15:20
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 前置拦截
     *
     * @author suvue
     * @date 2019/12/20 16:26
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        //判断是否存在cookie
        if (ArrayUtil.isEmpty(cookies)) {
            return false;
        }
        //遍历cookie
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (StrUtil.isEmpty(cookieName)) {
                continue;
            }
            //获取到存放登录用户的cookie
            if (StrUtil.equals(cookieName, SysConst.COOKIE_TOKEN_KEY)) {
                String cookieToken = cookie.getValue();
                Users loginUser = (Users)redisTemplate.opsForValue().get(cookieToken);
                if (ObjectUtil.isNotEmpty(loginUser)){
                    return true;
                }
            }
        }
        //尚未登录，跳转到登录页
        String sendPath = HttpTool.getAbsolutePath(request, "/toLogin");
        response.sendRedirect(sendPath);
        return false;
    }

    /**
     * 业务层处理之后 返回视图层之前拦截
     *
     * @author suvue
     * @date 2019/12/20 16:26
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求完全处理之后，一般进行资源的释放等操作
     *
     * @author suvue
     * @date 2019/12/20 16:28
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {

    }
}
