package cn.suvue.discipline.core.handler;

import cn.suvue.discipline.core.entity.ResultData;
import cn.suvue.discipline.core.exception.classes.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * web服务统一异常处理类
 *
 * @author suvue
 * @date 2019/12/18 23:31
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
    /**
     * 处理业务异常
     *
     * @author suvue
     * @date 2019/12/18 23:52
     */
    @ExceptionHandler
    public ResultData serviceHandler(ServiceException e) {
        log.error(e.getErrorMessage());
        return ResultData.failure(e.getCode(), e.getErrorMessage());
    }

}
