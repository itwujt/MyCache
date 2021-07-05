package com.bestwu.mycache.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/4 11:30 <br>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractMyCacheException extends RuntimeException implements MyCacheException {
    /**
     * error msg
     */
    protected String message;
    /**
     * error code
     */
    protected String code;
    /**
     * cause
     */
    protected Throwable cause;


    protected AbstractMyCacheException(String message, String code, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.code = code;
        this.cause = cause;
    }
    protected AbstractMyCacheException(String message, String code) {
        this(message);
        this.code = code;
    }

    protected AbstractMyCacheException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public void buildException(ErrorCode errorCode) {
        if (null != errorCode.getErrCode()) {
            this.code = errorCode.getErrCode();
        }
        if (null != errorCode.getCause()) {
            this.cause = errorCode.getCause();
        }
    }
}
