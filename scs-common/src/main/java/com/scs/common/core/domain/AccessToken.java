package com.scs.common.core.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AccessToken {
    private String phone;
    private String token;
    private Date expirationTime;
}
