package cn.suvue.discipline.modular.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页控制器
 *
 * @author suvue
 * @date 2019/12/20 21:09
 */
@RestController
@RequestMapping("/")
public class HomeController {
    public static final String PREFIX = "/pages";

    /**
     * 进入首页
     *
     * @author suvue
     * @date 2019/12/20 14:41
     */
    @PostMapping("/")
    public String toHome() {
        return PREFIX + "/home.html";
    }
}

