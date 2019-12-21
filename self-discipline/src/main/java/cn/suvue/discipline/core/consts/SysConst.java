package cn.suvue.discipline.core.consts;

/**
 * 系统常量池
 *
 * @author suvue
 * @date 2019/12/19 20:47
 */
public interface SysConst {
    /**
     * MD5加密盐值的长度
     */
    Integer SALT_LENGTH = 16;

    /**
     * 登录token令牌的长度
     */
    Integer TOKEN_KEY_LENGTH = 32;

    /**
     * redis中存储的数据名称
     */
    String REDIS_TOKEN_KEY = "TOKEN_TICKET";

    /**
     * cookie中存放token的键名称
     */
    String COOKIE_TOKEN_KEY = "TOKEN_PASSPORT";

    /**
     * token的失效时间
     */
    Integer TOKEN_EXPIRE = 3600 * 24;

    /**
     * http协议默认端口号
     */
    Integer HTTP_DEFAULT_PORT = 80;

    /**
     * https协议默认端口号
     */
    Integer HTTPS_DEFAULT_PORT = 443;
}
