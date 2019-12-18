package cn.suvue.discipline.modular.controller;


import cn.suvue.discipline.core.entity.ResultData;
import cn.suvue.discipline.modular.service.IUsersService;
import cn.suvue.discipline.modular.service.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author suvue
 * @since 2019-12-17
 */
@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private IUsersService usersService;

    /**
     * 用户注册
     *
     * @author suvue
     * @date 2019/12/18 23:11
     */
    @RequestMapping("/register")
    @ResponseBody
    public ResultData registerUser(String name, String password, String nick) {
        this.usersService.registerUser(name,password,nick);
        return ResultData.success();
    }
}

