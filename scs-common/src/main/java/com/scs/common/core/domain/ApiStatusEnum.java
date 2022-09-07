package com.scs.common.core.domain;


public enum ApiStatusEnum {
    /**
     * 业务请求成功统一编码
     */
    OK(200, "请求成功"),

    /**
     * 业务请求失败统一编码
     */
    FAIL(400, "请求失败"),

    /**
     * 请求非法统一编码
     */
    UNAUTHORIZED(401, "token过期或无效，请重新登录"),

    /**
     * 权限不足统一编码
     */
    NOT_PERMISSION(403, "无权限访问"),

    /**
     * 资源不存在统一编码
     */
    NOT_FOUND(404, "请求资源不存在"),

    /**
     * 业务服务异常统一编码
     */
    ERROR(500, "服务发生异常，请联系管理员。");


    private final int code;
    private final String msg;

    ApiStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

