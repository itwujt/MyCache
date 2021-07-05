package com.bestwu.mycache.common.exception;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/6 0:06 <br>
 */
public class RegisterPacketNullExeception extends AbstractMyCacheException {

    public RegisterPacketNullExeception(ErrorCode errorCode) {
        super(errorCode.getErrMsg());
        buildException(errorCode);
    }
}
