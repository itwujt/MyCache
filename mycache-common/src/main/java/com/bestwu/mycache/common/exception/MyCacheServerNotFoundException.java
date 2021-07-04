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
