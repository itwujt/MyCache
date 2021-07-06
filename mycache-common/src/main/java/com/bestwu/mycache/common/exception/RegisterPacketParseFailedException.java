package com.bestwu.mycache.common.exception;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/6 23:43 <br>
 */
public class RegisterPacketParseFailedException extends AbstractMyCacheException {

    public RegisterPacketParseFailedException(ErrorCode errorCode) {
        super(errorCode.getErrMsg());
        buildException(errorCode);
    }
}
