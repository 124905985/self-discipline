package cn.suvue.discipline.modular.service.impl;

import cn.suvue.discipline.modular.entity.Articles;
import cn.suvue.discipline.modular.mapper.ArticlesMapper;
import cn.suvue.discipline.modular.service.IArticlesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
