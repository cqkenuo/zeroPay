package com.zero.pay.Currency;

import lombok.Data;

/**
 * 逻辑异常类
 */
@Data
public class LogicException extends RuntimeException {
    /**
     * 异常信息
     */
    private String errorMsg;
    /**
     * 错误码
     */
    private Integer code;

    private LogicException(String errorMsg) {
        super(errorMsg);
        this.code = -1;
        this.errorMsg = errorMsg;
    }

    /**
     * 抛出逻辑异常
     *
     * @param errorMsg
     * @return
     */
    public static LogicException le(String errorMsg) {
        return new LogicException(errorMsg);
    }


}
