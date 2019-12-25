package cn.suvue.discipline.modular.service;

import cn.suvue.discipline.modular.entity.Articles;
import cn.suvue.discipline.modular.model.param.ArticlesParam;
import cn.suvue.discipline.modular.model.result.ArticlesResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 博文表 服务类
 * </p>
 *
 * @author suvue
 * @since 2019-12-17
 */
public interface IArticlesService extends IService<Articles> {

    /**
     * 新增博文
     *
     * @author suvue
     * @date 2019/12/24 22:12
     */
    void addArticle(ArticlesParam param);

    /**
     * 获取博文详情
     *
     * @author suvue
     * @date 2019/12/24 22:32
     */
    ArticlesResult detailArticle(Long articleId);

    /**
     * 修改博文
     *
     * @author suvue
     * @date 2019/12/24 22:38
     */
    void editArticle(ArticlesParam articlesParam);

    /**
     * 删除博文
     *
     * @author suvue
     * @date 2019/12/24 22:45
     */
    void removeArticle(Long articleId);

    /**
     * 获取新闻分页列表
     *
     * @author suvue
     * @date 2019/12/25 17:43
     */
    IPage<Articles> pageArticle(Page<Articles> page, String title);
}
