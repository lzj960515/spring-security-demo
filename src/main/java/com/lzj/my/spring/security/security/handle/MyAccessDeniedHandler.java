package com.lzj.my.spring.security.security.handle;

import com.lzj.my.spring.security.result.ApiResponse;
import com.lzj.my.spring.security.result.ResponseEnum;
import com.lzj.my.spring.security.util.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zijian Liao
 * @date 2020/1/5
 * @description: 权限校验异常处理
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.sendMessage(httpServletResponse,new ApiResponse(false, ResponseEnum.FORBIDDEN));
    }
}
