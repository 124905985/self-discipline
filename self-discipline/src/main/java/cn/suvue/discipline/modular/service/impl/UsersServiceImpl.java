package cn.suvue.discipline.modular.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.suvue.discipline.core.consts.SysConst;
import cn.suvue.discipline.core.enums.CoreCodeEnum;
import cn.suvue.discipline.core.exception.classes.ServiceException;
import cn.suvue.discipline.core.tools.HttpTool;
import cn.suvue.discipline.modular.entity.Users;
import cn.suvue.discipline.modular.mapper.UsersMapper;
import cn.suvue.discipline.modular.service.IUsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author suvue
 * @since 2019-12-17
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 用户注册
     *
     * @author suvue
     * @date 2019/12/18 23:12
     */
    @Override
    public void registerUser(String userName, String password, String nick) {
        Users users = new Users();
        users.setUserName(userName);
        //随机盐值
        String salt = RandomUtil.randomString(SysConst.SALT_LENGTH);
        users.setUserSalt(salt);
        //加密密码
        String md5Password = SecureUtil.md5(password + salt);
        users.setUserPassword(md5Password);
        users.setUserNickname(nick);
        try {
            this.save(users);
        } catch (DuplicateKeyException e) {
            throw new ServiceException(CoreCodeEnum.USER_HAS_EXISTED);
        }
    }

    /**
     * 用户登录
     *
     * @author suvue
     * @date 2019/12/19 21:51
     */
    @Override
    public Users loginUser(HttpServletResponse response, String userName, String password) {
        //校验用户是否存在
        QueryWrapper<Users> userWrapper = new QueryWrapper<>();
        userWrapper.eq("user_name", userName);
        Users userEntity = this.getOne(userWrapper);
        if (ObjectUtil.isEmpty(userEntity)) {
            throw new ServiceException(CoreCodeEnum.USER_NOT_EXIST);
        }
        //校验密码
        String salt = userEntity.getUserSalt();
        String inputPassword = SecureUtil.md5(password + salt);
        String dbPassword = userEntity.getUserPassword();
        if (!StrUtil.equals(inputPassword, dbPassword)) {
            throw new ServiceException(CoreCodeEnum.USER_LOGIN_ERROR);
        }

        //生成token并存入redis
        String token = RandomUtil.randomString(SysConst.TOKEN_KEY_LENGTH);
        long expire = System.currentTimeMillis() + SysConst.TOKEN_EXPIRE;
        redisTemplate.opsForValue().set(token, userEntity, expire, TimeUnit.SECONDS);

        //将token投放到cookie中
        Cookie cookie = new Cookie(SysConst.COOKIE_TOKEN_KEY, token);
        cookie.setMaxAge(SysConst.TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
        return userEntity;
    }

    /**
     * 用户执行退出
     *
     * @author suvue
     * @date 2019/12/22 16:56
     */
    @Override
    public void doLoginOut(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String loginToken = null;
        //删除cookie数据
        if (ArrayUtil.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                //cookie名为空则跳过
                if (ObjectUtil.isEmpty(cookieName)) {
                    continue;
                }
                //筛选存放登录token的redis
                if (StrUtil.equals(cookieName, SysConst.COOKIE_TOKEN_KEY)) {
                    String cookieValue = cookie.getValue();
                    if (ObjectUtil.isNotEmpty(cookieValue)) {
                        loginToken = cookieValue;
                        //删除cookie
                        Cookie deleteCookie = new Cookie(cookieName, null);
                        deleteCookie.setMaxAge(0);
                        response.addCookie(deleteCookie);
                        break;
                    }
                }
            }
        }
        //删除redis中缓存的用户数据
        if (ObjectUtil.isNotEmpty(loginToken)) {
            redisTemplate.delete(loginToken);
        }

        //跳转向登录页
        try {
            response.sendRedirect(HttpTool.getAbsolutePath(request, SysConst.TO_LOGIN_URL));
        } catch (IOException e) {
            log.error("用户退出时跳转向登录页面时异常！");
            throw new ServiceException(CoreCodeEnum.USER_LOGOUT_REDIRECT_ERROR);
        }
    }
}
