package cn.suvue.discipline.modular.controller;


import cn.suvue.discipline.core.entity.ResultData;
import cn.suvue.discipline.modular.service.IUsersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
    public static final String PREFIX = "";

    @Autowired
    private IUsersService usersService;

}

