package cn.suvue.discipline.modular.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.suvue.discipline.core.exception.classes.ServiceException;
import cn.suvue.discipline.core.threadlocal.LoginHolder;
import cn.suvue.discipline.modular.entity.Articles;
import cn.suvue.discipline.modular.mapper.ArticlesMapper;
import cn.suvue.discipline.modular.model.param.ArticlesParam;
import cn.suvue.discipline.modular.model.result.ArticlesResult;
import cn.suvue.discipline.modular.service.IArticlesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static cn.suvue.discipline.core.enums.CoreCodeEnum.*;

/**
 * <p>
 * 博文表 服务实现类
 * </p>
 *
 * @author suvue
 * @since 2019-12-17
 */
@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Articles> implements IArticlesService {

    /**
     * 新增博文
     *
     * @author suvue
     * @date 2019/12/24 22:13
     */
    @Override
    public void addArticle(ArticlesParam param) {
        if (ObjectUtil.hasEmpty(param.getArticleTitle(), param.getArticleContent())) {
            throw new ServiceException(PARAM_NOT_COMPLETE);
        }
        Articles article = new Articles();
        BeanUtil.copyProperties(param, article);
        article.setArticleDate(LocalDateTime.now());
        article.setUserId(LoginHolder.getUser().getUserId());
        this.save(article);
    }

    /**
     * 获取博文详情
     *
     * @author suvue
     * @date 2019/12/24 22:32
     */
    @Override
    public ArticlesResult detailArticle(Long articleId) {
        Articles articles = this.getById(articleId);
        if (ObjectUtil.isEmpty(articles)) {
            throw new ServiceException(PARAM_IS_INVALID);
        }
        ArticlesResult articlesResult = new ArticlesResult();
        BeanUtil.copyProperties(articles, articlesResult);
        return articlesResult;
    }

    /**
     * 修改博文
     *
     * @author suvue
     * @date 2019/12/24 22:38
     */
    @Override
    public void editArticle(ArticlesParam articlesParam) {
        if (ObjectUtil.hasEmpty(articlesParam.getArticleTitle(), articlesParam.getArticleContent())) {
            throw new ServiceException(PARAM_NOT_COMPLETE);
        }
        Articles articles = new Articles();
        BeanUtil.copyProperties(articlesParam, articles);
        this.updateById(articles);
    }

    /**
     * 删除博文
     *
     * @author suvue
     * @date 2019/12/24 22:45
     */
    @Override
    public void removeArticle(Long articleId) {
        if (ObjectUtil.isEmpty(articleId)){
            throw new ServiceException(PARAM_IS_BLANK);
        }
        this.removeById(articleId);
    }
}
