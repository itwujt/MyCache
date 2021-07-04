package com.bestwu.mycache.common.exception;

import lombok.Builder;
import lombok.Data;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/4 11:40 <br>
 */
@Data
@Builder
public class ErrorCode {
    /**
     * error message
     */
    private String errMsg;
    /**
     * error code
     */
    private String errCode;
    /**
     * cause
     */
    private Throwable cause;

    public ErrorCode(String errMsg) {
        this.errMsg = errMsg;
    }

    public ErrorCode(String errMsg, String errCode) {
        this(errMsg);
        this.errCode = errCode;
    }

    public ErrorCode(String errMsg, String errCode, Throwable cause) {
        this(errMsg, errCode);
        this.cause = cause;
    }
}
