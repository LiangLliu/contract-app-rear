package pers.edwin.contract.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liang
 * @param <T>
 */
@Data
public class ResultResponse<T> implements Serializable {

    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体内容.
     */
    private T data;
}
