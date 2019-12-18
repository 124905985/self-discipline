package cn.suvue.discipline.core.exception.interfaces;

/**
 * 定义异常规范
 *
 * @author suvue
 * @date 2019/12/18 23:41
 */
public interface IBaseExceptionEnum {
    /**
     * 获取异常的状态码
     */
    Integer getCode();

    /**
     * 获取异常的提示信息
     */
    String getMessage();
}
