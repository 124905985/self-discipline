package cn.suvue.discipline.core.entity;

import cn.suvue.discipline.core.enums.CoreCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * API返回封装类
 *
 * @author suvue
 * @date 2019/11/6 21:38
 */
@Data
public class ResultData implements Serializable {

    private Object data;
    private Integer code;
    private String message;

    /**
     * 返回成功
     */
    public static ResultData success() {
        ResultData result = new ResultData();
        result.setCode(CoreCodeEnum.SUCCESS.code());
        result.setMessage(CoreCodeEnum.SUCCESS.message());
        return result;
    }

    /**
     * 返回成功
     */
    public static ResultData success(Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(CoreCodeEnum.SUCCESS.code());
        resultData.setMessage(CoreCodeEnum.SUCCESS.message());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 返回失败
     */
    public static ResultData failure(CoreCodeEnum coreCodeEnum) {
        ResultData result = new ResultData();
        result.setCode(coreCodeEnum.code());
        result.setMessage(coreCodeEnum.message());
        return result;
    }

    /**
     * 返回失败
     */
    public static ResultData failure(CoreCodeEnum coreCodeEnum, Object data) {
        ResultData result = new ResultData();
        result.setCode(coreCodeEnum.code());
        result.setMessage(coreCodeEnum.message());
        result.setData(data);
        return result;
    }

    /**
     * 返回失败
     */
    public static <V> ResultData failure(Integer code, String message) {
        ResultData result = new ResultData();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 返回失败
     */
    public static ResultData failure(Integer code, String message, Object data) {
        ResultData result = new ResultData();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}
