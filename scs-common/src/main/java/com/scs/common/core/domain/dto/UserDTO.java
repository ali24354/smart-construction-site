package com.scs.common.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    /**
     * 姓名
     */
    private String fullname;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 家庭地址
     */
    private String address;

    /**
     * 用户类型， 0管理员  1普通用户
     */
    private int userType;

    /**
     * 性别， 0男性  1女性
     */
    private int sex;

    /**
     * 身份证号码
     */
    private String ID;

    /**
     * 删除标志
     */
    private int delFlag;

    /**
     * 用户状态  0正常  1停用  2待审批
     */
    private int status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createTime;
}
