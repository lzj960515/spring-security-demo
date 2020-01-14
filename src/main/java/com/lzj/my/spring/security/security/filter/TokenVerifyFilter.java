package com.lzj.my.spring.security.security.filter;

import com.lzj.my.spring.security.comstant.CommonConstant;
import com.lzj.my.spring.security.domain.User;
import com.lzj.my.spring.security.result.ApiResponse;
import com.lzj.my.spring.security.result.ResponseEnum;
import com.lzj.my.spring.security.util.JSONUtils;
import com.lzj.my.spring.security.util.RedisUtil;
import com.lzj.my.spring.security.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zijian Liao
 * @date 2020/1/3 17:41
 * @description 检验Token 过滤器
 */
public class TokenVerifyFilter extends BasicAuthenticationFilter {

    @Autowired
    private RedisUtil redisUtil;

    public TokenVerifyFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //请求体的头中是否包含Authorization
        String token = request.getHeader(CommonConstant.TOKEN);
        if (token == null) {
            chain.doFilter(request, response);
        } else {
            //验证token是否正确
            User user = JSONUtils.toBean(redisUtil.hget(CommonConstant.KET_USER, token), User.class);
            if (user != null) {
                UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authResult);
                chain.doFilter(request, response);
            } else {
                ResponseUtil.sendMessage(response, new ApiResponse(false, ResponseEnum.EXPIRATION_TOKEN));
            }
        }
    }

}
