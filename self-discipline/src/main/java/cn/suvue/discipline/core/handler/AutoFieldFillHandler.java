package cn.suvue.discipline.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatis-plus提供的属性自动填充策略
 *
 * @author suvue
 * @date 2019/12/19 21:06
 */
@Slf4j
@Component
public class AutoFieldFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("user_registration_time", LocalDateTime.now(),metaObject);
        this.setFieldValByName("article_date", LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
