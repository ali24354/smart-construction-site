package com.scs.scsframework.filter;

import cn.hutool.core.util.StrUtil;
import com.scs.common.core.domain.entity.UserDetail;
import com.scs.common.utils.JwtUtils;
import com.scs.common.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * JWT登录过滤器
 * </p>
 * <p>
 * 拿到请求头中的token解析出其中的用户信息，
 * 将用户信息传给下一条过滤器，
 * 拿到上下文对象赋值到上下文。
 * <p>
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        log.info("JWT过滤器通过校验请求头token进行自动登录...");
        //获取用户信息
        // 拿到Authorization请求头内的信息
        String authToken = jwtUtils.getToken(request);

        // 判断一下内容是否为空
        if (StrUtil.isNotEmpty(authToken) && authToken.startsWith(TOKEN_PREFIX)) {
            // 去掉token前缀(Bearer )，拿到真实token
            authToken = authToken.substring(TOKEN_PREFIX.length());

            // 拿到token里面的登录账号
            String loginAccount = jwtUtils.getSubjectFromToken(authToken);

            if (StrUtil.isNotEmpty(loginAccount) && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 查询用户
                UserDetail userDetail = (UserDetail) redisUtils.get(loginAccount);

                // 拿到用户信息后验证用户信息与token
                if (userDetail != null && jwtUtils.validateToken(authToken, userDetail)) {

                    // 组装authentication对象，构造参数是Principal Credentials 与 Authorities
                    // 后面的拦截器里面会用到 grantedAuthorities 方法
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, userDetail.getPassword(), userDetail.getAuthorities());

                    // 将authentication信息放入到上下文对象中
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    log.info("JWT过滤器通过校验请求头token自动登录成功, user : {}", userDetail.getUsername());
                }
            }
        }

        chain.doFilter(request, response);
    }
}
