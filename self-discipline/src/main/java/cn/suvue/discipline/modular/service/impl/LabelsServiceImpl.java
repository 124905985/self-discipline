package cn.suvue.discipline.modular.service.impl;

import cn.suvue.discipline.modular.entity.Labels;
import cn.suvue.discipline.modular.mapper.LabelsMapper;
import cn.suvue.discipline.modular.service.ILabelsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author suvue
 * @since 2019-12-17
 */
@Service
public class LabelsServiceImpl extends ServiceImpl<LabelsMapper, Labels> implements ILabelsService {

}
