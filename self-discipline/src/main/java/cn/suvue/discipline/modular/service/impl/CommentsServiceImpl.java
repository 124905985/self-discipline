package cn.suvue.discipline.modular.service.impl;

import cn.suvue.discipline.modular.entity.Comments;
import cn.suvue.discipline.modular.mapper.CommentsMapper;
import cn.suvue.discipline.modular.service.ICommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author suvue
 * @since 2019-12-17
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {

}
