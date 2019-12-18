package cn.suvue.discipline.core.exception.classes;

import cn.suvue.discipline.core.exception.interfaces.IBaseExceptionEnum;

/**
 * 业务异常类
 *
 * @author suvue
 * @date 2019/12/18 23:35
 */
public class ServiceException extends RuntimeException {
    private Integer code;

    private String errorMessage;

    public ServiceException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public ServiceException(IBaseExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
