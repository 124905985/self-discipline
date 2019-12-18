package cn.suvue.discipline.modular.service.impl;

import cn.suvue.discipline.modular.entity.Users;
import cn.suvue.discipline.modular.mapper.UsersMapper;
import cn.suvue.discipline.modular.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    /**
     * 用户注册
     *
     * @author suvue
     * @date 2019/12/18 23:12
     */
    @Override
    public void registerUser(String name, String password, String nick) {


    }
}
