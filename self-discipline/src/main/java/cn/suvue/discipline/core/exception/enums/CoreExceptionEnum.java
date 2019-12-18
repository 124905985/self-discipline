package cn.suvue.discipline.core.exception.enums;

import cn.suvue.discipline.core.exception.interfaces.IBaseExceptionEnum;
/**
 * 核心异常枚举集合
 *
 * @author suvue
 * @date 2019/12/18 23:44
 */
public enum CoreExceptionEnum implements IBaseExceptionEnum {
    /**
     * 其他
     */
    INVLIDE_DATE_STRING(400, "输入日期格式不对"),

    /**
     * 初始化数据库的异常
     */
    NO_CURRENT_USER(700, "当前没有登录的用户"),
    INIT_TABLE_EMPTY_PARAMS(701, "初始化数据库，存在为空的字段"),

    /**
     * 其他
     */
    WRITE_ERROR(500, "渲染界面错误"),
    ENCRYPT_ERROR(600, "加解密错误"),

    /**
     * 文件上传
     */
    FILE_READING_ERROR(400, "FILE_READING_ERROR!"),
    FILE_NOT_FOUND(400, "FILE_NOT_FOUND!"),

    /**
     * 错误的请求
     */
    PAGE_NULL(404, "请求页面不存在"),
    IO_ERROR(500, "流读取异常"),
    SERVICE_ERROR(500, "服务器异常"),
    REMOTE_SERVICE_NULL(404, "远程服务不存在"),
    ASYNC_ERROR(5000, "数据在被别人修改，请稍后重试");

    CoreExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
