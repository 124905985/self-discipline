package cn.suvue.discipline.modular.service;

import cn.suvue.discipline.modular.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

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
    void registerUser(String name, String password, String nick);
}
