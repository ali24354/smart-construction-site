package com.scs.common.core.domain.request;

import lombok.Data;

@Data
public class UserRequest {
    private String fullName;
    private String phone;
    private String password;
    private String code;
    private String address;
    private int type;
    private int sex;
    private String ID;
    private String mail;
}
