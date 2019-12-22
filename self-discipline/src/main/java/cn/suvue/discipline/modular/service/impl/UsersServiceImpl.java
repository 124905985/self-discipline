package cn.suvue.discipline.modular.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import cn.suvue.discipline.core.consts.SysConst;
import cn.suvue.discipline.core.enums.CoreCodeEnum;
import cn.suvue.discipline.core.exception.classes.ServiceException;
import cn.suvue.discipline.modular.entity.Users;
import cn.suvue.discipline.modular.mapper.UsersMapper;
import cn.suvue.discipline.modular.service.IUsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
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
        if (ObjectUtil.isEmpty(userEntity)){
            throw new ServiceException(CoreCodeEnum.USER_NOT_EXIST);
        }
        //校验密码
        String salt = userEntity.getUserSalt();
        String inputPassword = SecureUtil.md5(password + salt);
        String dbPassword = userEntity.getUserPassword();
        if (!StrUtil.equals(inputPassword,dbPassword)){
            throw new ServiceException(CoreCodeEnum.USER_LOGIN_ERROR);
        }

        //生成token并存入redis
        String token = RandomUtil.randomString(SysConst.TOKEN_KEY_LENGTH);
        long expire = System.currentTimeMillis() + SysConst.TOKEN_EXPIRE;
        redisTemplate.opsForValue().set(token,userEntity, expire, TimeUnit.SECONDS);

        //将token投放到cookie中
        Cookie cookie = new Cookie(SysConst.COOKIE_TOKEN_KEY, token);
        cookie.setMaxAge(SysConst.TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
        return userEntity;
    }
}
