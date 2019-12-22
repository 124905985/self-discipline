package cn.suvue.discipline.modular.service;

import cn.suvue.discipline.modular.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author suvue
 * @since 2019-12-17
 */
public interface IUsersService extends IService<Users> {

    /**
     * 用户注册
     *
     * @author suvue
     * @date 2019/12/18 23:12
     */
    void registerUser(String userName, String password, String nick);

    /**
     * 用户登录
     *
     * @author suvue
     * @date 2019/12/19 21:51
     */
    Users loginUser(HttpServletResponse response, String userName, String password);

    /**
     * 用户执行退出
     *
     * @author suvue
     * @date 2019/12/22 16:56
     */
    void doLoginOut(HttpServletRequest request, HttpServletResponse response);
}
