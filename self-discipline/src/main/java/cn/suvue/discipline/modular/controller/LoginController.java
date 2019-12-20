package cn.suvue.discipline.modular.controller;


import cn.suvue.discipline.core.entity.ResultData;
import cn.suvue.discipline.modular.service.IUsersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public static final String PREFIX = "/pages";

    @Autowired
    private IUsersService usersService;

    /**
     * 用户注册
     *
     * @author suvue
     * @date 2019/12/18 23:11
     */
    @PostMapping("/register")
    @ResponseBody
    public ResultData registerUser(String userName, String password, String nick) {
        this.usersService.registerUser(userName, password, nick);
        return ResultData.success();
    }

    /**
     * 跳转向登录页
     *
     * @author suvue
     * @date 2019/12/20 21:05
     */
    @GetMapping("/toLogin")
    public String toLogin() {
        return PREFIX + "/login/login.html";
    }

    /**
     * 用户执行登录
     *
     * @author suvue
     * @date 2019/12/18 23:11
     */
    @PostMapping("/doLogin")
    public String loginUser(HttpServletResponse response,
                            @Param("userName") String userName,
                            @Param("password") String password) {
        this.usersService.loginUser(response, userName, password);
        return PREFIX + "/index.html";
    }
}

