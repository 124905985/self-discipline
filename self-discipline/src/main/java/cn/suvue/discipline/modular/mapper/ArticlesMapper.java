package cn.suvue.discipline.modular.mapper;

import cn.suvue.discipline.modular.entity.Articles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 博文表 Mapper 接口
 * </p>
 *
 * @author suvue
 * @since 2019-12-17
 */
public interface ArticlesMapper extends BaseMapper<Articles> {

    /**
     * 获取博文分页列表
     *
     * @author suvue
     * @date 2019/12/25 17:47
     */
    IPage<Articles> pageArticle(Page<Articles> page, @Param("title") String title);
}
