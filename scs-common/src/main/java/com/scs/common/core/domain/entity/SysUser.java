package com.scs.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户对象 sys_user
 */
@Data
public class SysUser {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 姓名
     */
    private String fullname;

    /**
     * 用户类型（0系统管理员 1普通用户）
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String mail;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 用户性别
     */
    private int sex;

    /**
     * 密码
     */
    private String password;

    /**
     * 身份证号码
     */
    private String ID;

    /**
     * 家庭地址
     */
    private String address;

    /**
     * 帐号状态（0正常 1停用 2待审批）
     */
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String delFlag;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createTime;

}
