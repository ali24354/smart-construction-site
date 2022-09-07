package com.scs.system.service;

import com.scs.common.core.domain.AccessToken;
import com.scs.common.core.domain.JsonResult;
import com.scs.common.core.domain.request.UserRequest;
import com.scs.common.core.domain.vo.UserVO;

public interface ILoginService {
    /**
     * 保存用户注册信息
     *
     * @param user
     * @return
     */
    boolean saveUserDetail(UserVO user);

    AccessToken login(UserRequest request);


    JsonResult logout();

    void getCode(String phone);
}
