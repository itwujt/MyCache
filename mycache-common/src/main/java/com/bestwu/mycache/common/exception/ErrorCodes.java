package com.bestwu.mycache.common.exception;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/4 11:48 <br>
 */
public class ErrorCodes {

    public final static ErrorCode INTERNAL_ERR_CODE = ErrorCode.builder().errMsg("系统内部错误！系统异常，请尝试联系管理员！邮箱地址：itwujt@sina.com").errCode("INTERNAL_ERR_CODE").build();

    public final static ErrorCode REGISTER_REFUSED_ERR_CODE = ErrorCode.builder().errMsg("客户端注册连接被拒绝，可能是被认为无效的参数，请检验你的连接参数是否正确，验证后请重试，如仍连接失败，请联系管理员！邮箱地址：itwujt@sina.com").errCode("REGISTER_REFUSED_ERR_CODE").build();

    public final static ErrorCode REGISTER_PACKET_NULL_ERR_CODE = ErrorCode.builder().errMsg("客户端注册连接报文格式异常，请仔细检查，检查后重试，如仍连接失败，请联系管理员！邮箱地址：itwujt@sina.com").errCode("REGISTER_PACKET_NULL_ERR_CODE").build();
}
