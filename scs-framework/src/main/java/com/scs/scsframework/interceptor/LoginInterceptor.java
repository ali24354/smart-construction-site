/*
package com.scs.scsframework.interceptor;

import com.scs.scsframework.utils.JwtUtils;
import io.swagger.models.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }

        String token = request.getHeader("access_token");
        if(JwtUtils.verify(token)){
            return true;
        }

        request.setAttribute("msg", "token不正确，请重新登录");
        request.getRemoteHost();
        request.getRequestDispatcher("/login").forward(request, response);
        return false;
    }
}
*/
