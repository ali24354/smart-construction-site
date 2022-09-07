package com.scs.admin.controller;

import com.scs.common.core.controller.BaseController;
import com.scs.common.core.domain.JsonResult;
import com.scs.common.core.domain.request.UserRequest;
import com.scs.common.core.domain.vo.UserVO;
import com.scs.system.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @PostMapping("/perfectUserInfo")
    @ApiOperation(value = "完善用户个人信息")
    public JsonResult perfectUserInfo(@RequestBody UserRequest userRequest) {
        userService.perfectUserInfo(userRequest);
        return JsonResult.success();
    }
}
