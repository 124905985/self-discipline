package cn.suvue.discipline.modular.controller;


import cn.suvue.discipline.core.entity.ResultData;
import cn.suvue.discipline.modular.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 登录控制器
 *
 * @author suvue
 * @date 2019/12/20 16:32
 */
@Controller
public class LoginController {

    @Autowired
    private IUsersService usersService;

    /**
     * 用户执行注册
     *
     * @author suvue
     * @date 2019/12/18 23:11
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResultData registerUser(@RequestBody Map<String, String> param) {
        String userName = param.get("username");
        String password = param.get("password");
        String nick = param.get("nick");
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
    public ResultData loginUser(
            HttpServletResponse response,
            @RequestBody Map<String, String> userInfo) {
        String username = userInfo.get("username");
        String password = userInfo.get("password");
        this.usersService.loginUser(response, username, password);
        return ResultData.success();
    }

    /**
     * 用户执行退出
     *
     * @author suvue
     * @date 2019/12/22 16:55
     */
    @RequestMapping(value = "/doLoginOut", method = RequestMethod.GET)
    public void doLoginOut(HttpServletRequest request, HttpServletResponse response) {
        this.usersService.doLoginOut(request, response);
    }
}

