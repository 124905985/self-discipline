package cn.suvue.discipline.modular.controller;


import cn.hutool.json.JSONUtil;
import cn.suvue.discipline.core.entity.ResultData;
import cn.suvue.discipline.modular.entity.Users;
import cn.suvue.discipline.modular.service.IUsersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 登录控制器
 *
 * @author suvue
 * @date 2019/12/20 16:32
 */
@Controller
public class LoginController {
    public static final String PREFIX = "/";

    @Autowired
    private IUsersService usersService;

    /**
     * 用户注册
     *
     * @author suvue
     * @date 2019/12/18 23:11
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResultData registerUser(String userName, String password, String nick) {
        this.usersService.registerUser(userName, password, nick);
        return ResultData.success();
    }

    /**
     * 用户执行登录
     *
     * @author suvue
     * @date 2019/12/18 23:11
     */
    @ResponseBody
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ResultData loginUser(HttpServletResponse response,
                                @Param("username") String username,
                                @Param("password") String password) {
        Users users = this.usersService.loginUser(response, username, password);
        return ResultData.success(JSONUtil.toJsonStr(users));
    }
}

