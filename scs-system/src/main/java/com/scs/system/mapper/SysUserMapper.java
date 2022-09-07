package com.scs.system.mapper;

import com.scs.common.core.domain.entity.SysUser;
import com.scs.common.core.domain.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {
    List<SysUser> selectUserList(List<String> userList);

    UserInfo selectOne(@Param("phone") String phone);

    void updateUserInfo(@Param("user") SysUser user);
}
