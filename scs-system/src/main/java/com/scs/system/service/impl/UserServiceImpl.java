package com.scs.system.service.impl;

import com.scs.common.core.domain.entity.SysUser;
import com.scs.common.core.domain.entity.UserDetail;
import com.scs.common.core.domain.entity.UserInfo;
import com.scs.common.core.domain.request.UserRequest;
import com.scs.common.core.domain.vo.UserVO;
import com.scs.system.mapper.SysUserMapper;
import com.scs.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public List<SysUser> selectUserList(List<String> userList) {
        return userMapper.selectUserList(userList);
    }

    @Override
    public UserInfo getOne(String phone) {
        return userMapper.selectOne(phone);
    }

    @Override
    public void perfectUserInfo(UserRequest userRequest) {
        userMapper.updateUserInfo(questWrapSysUser(userRequest));
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.debug("开始登陆验证，用户名为: {}", userName);
        // 根据用户名验证用户
        UserInfo user = userMapper.selectOne(userName);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在，登陆失败。");
        }

        // 构建UserDetail对象
        UserDetail userDetail = new UserDetail();
        userDetail.setUserInfo(user);

        return userDetail;
    }

    private SysUser questWrapSysUser(UserRequest userRequest) {
        SysUser user = new SysUser();
        user.setSex(userRequest.getSex());
        user.setMail(userRequest.getMail());
        user.setPhoneNumber(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());
        return user;
    }
}
