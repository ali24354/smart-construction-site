package com.scs.system.service;


import com.scs.common.core.domain.entity.SysUser;
import com.scs.common.core.domain.entity.UserInfo;
import com.scs.common.core.domain.request.UserRequest;
import com.scs.common.core.domain.vo.UserVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<SysUser> selectUserList(List<String> user);

    UserInfo getOne(String phone);

    void perfectUserInfo(UserRequest userRequest);
}
