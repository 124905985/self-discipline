package cn.suvue.discipline.modular.controller;


import cn.suvue.discipline.core.entity.LayuiData;
import cn.suvue.discipline.core.entity.ResultData;
import cn.suvue.discipline.modular.entity.Articles;
import cn.suvue.discipline.modular.model.param.ArticlesParam;
import cn.suvue.discipline.modular.model.result.ArticlesResult;
import cn.suvue.discipline.modular.service.IArticlesService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 博文前端控制器
 * </p>
 *
 * @author suvue
 * @since 2019-12-17
 */
@Controller
@RequestMapping("/articles")
public class ArticlesController {

    @Autowired
    private IArticlesService articlesService;

    /**
     * 跳转到新增博文页面
     *
     * @author suvue
     * @date 2019/12/25
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String articleAdd() {
        return "/article/article_add";
    }

    /**
     * 新增博文
     *
     * @author zhaokeyan
     * @date 2019/12/24
     */
    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    @ResponseBody
    public ResultData addArticle(@RequestBody ArticlesParam param) {
        this.articlesService.addArticle(param);
        return ResultData.success();
    }

    /**
     * 获取博文详情
     *
     * @author zhaokeyan
     * @date 2019/12/24
     */
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResultData detailArticle(@RequestParam Long articleId) {
        ArticlesResult articlesResult = this.articlesService.detailArticle(articleId);
        return ResultData.success(articlesResult);
    }

    /**
     * 修改博文
     *
     * @author suvue
     * @date 2019/12/24 22:36
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultData editArticle(@RequestBody ArticlesParam articlesParam) {
        this.articlesService.editArticle(articlesParam);
        return ResultData.success();
    }

    /**
     * 删除博文
     *
     * @author suvue
     * @date 2019/12/24 22:43
     */
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    @ResponseBody
    public ResultData removeArticle(@RequestParam Long articleId) {
        this.articlesService.removeArticle(articleId);
        return ResultData.success();
    }

    /**
     * 获取新闻分页列表
     *
     * @author zhaokeyan
     * @date 2019/12/25
     */
    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    @ResponseBody
    public LayuiData pageArticle(@RequestParam Long current,
                                 @RequestParam Long size,
                                 @RequestParam(required = false) String title) {
        Page<Articles> articlesPage = new Page<>(current, size);
        IPage<Articles> pageArticle = this.articlesService.pageArticle(articlesPage, title);
        return LayuiData.success(pageArticle.getTotal(), pageArticle.getRecords());
    }

}

