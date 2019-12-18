package cn.suvue.discipline.core.entity;

import cn.suvue.discipline.core.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * API返回封装类
 *
 * @author suvue
 * @date 2019/11/6 21:38
 */
@Data
public class ResultData<T> implements Serializable {

    private Collection<T> data;
    private Integer code;
    private String message;

    /**
     * 返回成功
     */
    public static ResultData success() {
        ResultData result = new ResultData();
        result.setCode(ResultCode.SUCCESS.code());
        result.setMessage(ResultCode.SUCCESS.message());
        return result;
    }

    /**
     * 返回成功
     */
    public static <V> ResultData<V> success(Collection<V> data) {
        ResultData resultData = new ResultData();
        resultData.setCode(ResultCode.SUCCESS.code());
        resultData.setMessage(ResultCode.SUCCESS.message());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 返回失败
     */
    public static ResultData failure(ResultCode resultCode) {
        ResultData result = new ResultData();
        result.setCode(resultCode.code());
        result.setMessage(resultCode.message());
        return result;
    }

    /**
     * 返回失败
     */
    public static <V> ResultData<V> failure(ResultCode resultCode, Collection<V> data) {
        ResultData result = new ResultData();
        result.setCode(resultCode.code());
        result.setMessage(resultCode.message());
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
    public static <V> ResultData<V> failure(Integer code, String message, Collection<V> data) {
        ResultData result = new ResultData();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}
