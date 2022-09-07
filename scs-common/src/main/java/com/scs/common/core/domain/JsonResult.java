package com.scs.common.core.domain;


import com.scs.common.exception.ExceptionResolvable;

import java.io.Serializable;

/**
 * <功能描述>
 */
public class JsonResult<T> implements Serializable {
    private Integer code = 0;
    private static final String SUCCESS = "success";
    private String message = SUCCESS;
    private T data;

    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(0),
        /**
         * 警告
         */
        WARN(301),
        /**
         * 错误
         */
        ERROR(500);
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    public JsonResult(Type type, String message) {
        super();
        this.code = type.value;
    }

    /**
     * 只为序列化，不能使用
     */
    public JsonResult() {
        super();
    }

    /**
     * 构造不对外
     *
     * @param code
     * @param message
     * @param data
     */
    private JsonResult(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public JsonResult(String message) {
        super();
        this.message = message;
    }


    /**
     * 自定义成功信息
     */
    public static <T> JsonResult<T> success(String message, T data) {
        return new JsonResult<>(0, message, data);
    }

    /**
     * @描述： 默认success成功信息
     */
    public static <T> JsonResult<T> success(T result) {
        return new JsonResult<>(0, SUCCESS, result);
    }

    /**
     * 默认的成功信息，data为空
     */
    public static <T> JsonResult<T> success() {
        return new JsonResult<>(0, SUCCESS, null);
    }

    /**
     * 无异常信息的时候使用
     */
    public static <T> JsonResult<T> error(ExceptionResolvable exceptionCode) {
        return new JsonResult<>(exceptionCode.getCode(),
                exceptionCode.getMessage(), null);
    }

    /**
     * 500
     */
    private static final Integer EXCEPTION_CODE_500 = 500;
    /**
     * 400
     */
    private static final Integer EXCEPTION_CODE_400 = 400;
    /**
     * 500 Default message
     */
    private static final String EXCEPTION_MESSAGE_500 = "Server Internal Error";
    /**
     * 400 Default message
     */
    private static final String EXCEPTION_MESSAGE_400 = "Bad request";

    /**
     * 无异常信息的时候使用
     */
    public static <T> JsonResult<T> error(Integer code, String message) {
        if (code == null) {
            code = EXCEPTION_CODE_500;
        }
        if (message == null) {
            message = EXCEPTION_CODE_400.equals(code) ? EXCEPTION_MESSAGE_400 : EXCEPTION_MESSAGE_500;
        }
        return new JsonResult<>(code, message, null);
    }

    /**
     * 默认错误时使用
     */
    public static <T> JsonResult<T> error() {
        return error(EXCEPTION_CODE_500, null);
    }

    /**
     * 仅指定code时使用
     */
    public static <T> JsonResult<T> error(Integer code) {
        return error(code, null);
    }

    /**
     * 仅指定message时使用
     */
    public static <T> JsonResult<T> error(String message) {
        return error(null, message);
    }

//    /**
//     * 有异常信息的时候
//     */
//    public static JsonResult<Void> error(Integer code, String message) {
//        return new JsonResult<>(code, message, null);
//    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
