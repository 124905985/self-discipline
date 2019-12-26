package cn.suvue.discipline.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * layui分页封装类
 *
 * @author suvue
 * @date 2019/12/26
 */
@Data
public class LayuiData implements Serializable {

    private Object data;
    private Long count;
    private Integer code = 0;
    private String msg = "ok";

    /**
     * 返回成功
     */
    public static LayuiData success(Long count, Object data) {
        LayuiData result = new LayuiData();
        result.setCount(count);
        result.setData(data);
        return result;
    }

}
