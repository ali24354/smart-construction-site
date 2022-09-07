package com.scs.system.service.impl;

import com.scs.common.core.domain.AccessToken;
import com.scs.common.core.domain.JsonResult;
import com.scs.common.core.domain.entity.SysUser;
import com.scs.common.core.domain.entity.UserDetail;
import com.scs.common.core.domain.request.UserRequest;
import com.scs.common.core.domain.vo.UserVO;
import com.scs.common.utils.JwtUtils;
import com.scs.common.utils.RedisUtils;
import com.scs.common.utils.UserContextUtils;
import com.scs.system.mapper.LoginMapper;
import com.scs.system.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
@Slf4j
public class LoginServiceImpl implements ILoginService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisUtils redisUtils;

    private final LoginMapper loginMapper;

    public LoginServiceImpl(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    @Override
    public boolean saveUserDetail(UserVO userVO) {
        SysUser user = wrapSysUser(userVO);
        return loginMapper.insert(user) > 0;
    }

    private SysUser wrapSysUser(UserVO userVO) {
        SysUser user = new SysUser();
        user.setID(userVO.getCardId());
        user.setPassword(userVO.getPassword());
        user.setFullname(userVO.getFullname());
        user.setPhoneNumber(userVO.getPhone());
        user.setCreateTime(LocalDateTime.now());
        return user;
    }

    @Override
    public AccessToken login(UserRequest request) {
        AccessToken token = JwtUtils.createToken(request.getPhone());
        if (request.getType() == 1) {
            /**
             * 验证码登录的认证
             */
        } else {
            log.debug("进入login方法");
            // 1 创建UsernamePasswordAuthenticationToken
            UsernamePasswordAuthenticationToken usernameAuthentication = new UsernamePasswordAuthenticationToken(request.getPhone(), request.getPassword());
            // 2 认证
            Authentication authentication = this.authenticationManager.authenticate(usernameAuthentication);
            // 3 保存认证信息
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 4 生成自定义token
            AccessToken accessToken = JwtUtils.createToken((UserDetails) authentication.getPrincipal());

            UserDetail userDetail = (UserDetail) authentication.getPrincipal();
            // 放入缓存
            redisUtils.set(request.getPhone(), userDetail, 900);
            return accessToken;
        }
        return token;
    }

    @Override
    public JsonResult logout() {
        redisUtils.del(UserContextUtils.getLoginAccount());
        SecurityContextHolder.clearContext();
        return JsonResult.success();
    }

    @Override
    public void getCode(String phone) {
        return;
    }
}
