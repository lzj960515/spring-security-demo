package com.lzj.my.spring.security.security.handle;

import com.lzj.my.spring.security.result.ApiResponse;
import com.lzj.my.spring.security.result.ResponseEnum;
import com.lzj.my.spring.security.util.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zijian Liao
 * @date 2020/1/5
 * @description: 身份校验异常处理
 */
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.sendMessage(httpServletResponse,new ApiResponse(false, ResponseEnum.NOT_LOGIN));
    }
}
