package com.bestwu.mycache.common.exception;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/4 13:17 <br>
 */
public class MyCacheServerNotFoundException extends AbstractMyCacheException {

    public MyCacheServerNotFoundException(ErrorCode errorCode) {
        super(errorCode.getErrMsg());
        buildException(errorCode);
    }
}
