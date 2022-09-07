package com.scs.common.core.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserVO {
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String fullname;

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String code;

    /**
     * 身份证号码
     */
    @NotBlank(message = "身份证号码不能为空")
    private String cardId;

}
