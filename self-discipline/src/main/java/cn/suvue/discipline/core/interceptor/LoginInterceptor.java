package cn.suvue.discipline.core.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.suvue.discipline.core.consts.SysConst;
import cn.suvue.discipline.core.threadlocal.LoginHolder;
import cn.suvue.discipline.core.tools.HttpTool;
import cn.suvue.discipline.modular.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 自定义登录拦截器
 *
 * @author suvue
 * @date 2019/12/20 15:20
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
            //尚未登录，跳转到登录页
            String sendPath = HttpTool.getAbsolutePath(request, "/toLogin");
            response.sendRedirect(sendPath);
            log.error("请先登录");
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
                Map loginUserMap = (Map) this.redisTemplate.opsForValue().get(cookieToken);
                Users loginUser = BeanUtil.mapToBean(loginUserMap, Users.class, false);
                if (ObjectUtil.isNotEmpty(loginUser)) {
                    //存放以便后续使用
                    LoginHolder.setUser(loginUser);
                    return true;
                }
            }
        }
        //尚未登录，跳转到登录页
        String sendPath = HttpTool.getAbsolutePath(request, SysConst.TO_LOGIN_URL);
        response.sendRedirect(sendPath);
        log.error("请先登录!");
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
        //清空threadlocal
        LoginHolder.removeUser();

    }
}
