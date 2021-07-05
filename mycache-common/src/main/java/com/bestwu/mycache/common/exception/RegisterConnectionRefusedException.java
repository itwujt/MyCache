package com.bestwu.mycache.common.exception;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/5 23:23 <br>
 */
public class RegisterConnectionRefusedException extends AbstractMyCacheException {

    public RegisterConnectionRefusedException(ErrorCode errorCode) {
        super(errorCode.getErrMsg());
        buildException(errorCode);
    }
}
