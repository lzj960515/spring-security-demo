package com.lzj.my.spring.security.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzj.my.spring.security.comstant.CommonConstant;
import com.lzj.my.spring.security.domain.User;
import com.lzj.my.spring.security.result.ApiResponse;
import com.lzj.my.spring.security.result.ResponseEnum;
import com.lzj.my.spring.security.service.UserService;
import com.lzj.my.spring.security.util.JSONUtils;
import com.lzj.my.spring.security.util.RedisUtil;
import com.lzj.my.spring.security.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Zijian Liao
 * @date 2020/1/3 17:34
 * @description 认证过滤器
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Value("${token.expiration}")
    private Integer tokenExpiration;

    @Autowired
    private RedisUtil redisUtil;

    public TokenLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 接收并解析用户凭证，出现错误时，返回Json数值
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            //将json格式请求体转成JavaBean对象
            JSONObject jsonObject = new ObjectMapper().readValue(request.getInputStream(), JSONObject.class);
            //User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(jsonObject.get("username"), jsonObject.get("password"));
            return authenticationManager.authenticate(authRequest);
        } catch (Exception e) {
            //如果认证失败，提供自定义json格式异常
            if (e instanceof DisabledException) {
                //用户已无效
                ResponseUtil.sendMessage(response, new ApiResponse(false, ResponseEnum.USER_DISABLED));
            } else if (e instanceof LockedException) {
                //账户已锁定
                ResponseUtil.sendMessage(response, new ApiResponse(false, ResponseEnum.USER_LOCKED));
            } else {
                ResponseUtil.sendMessage(response, new ApiResponse(false, ResponseEnum.LOGIN_FAIL));
            }
            return null;
        }
    }

    /**
     * 用户登录成功后，生成token,并且返回json数据给前端
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //得到当前认证的用户对象
        //response.addCookie(); 记住我功能暂未实现
        User user = new User();
        User temp = (User) authResult.getPrincipal();
        //只放入需要的信息
        user.setUsername(temp.getUsername());
        user.setRoles(temp.getRoles());

        //json web token构建
        // String token = JwtUtils.generateTokenExpireInMinutes(user, prop.getPrivateKey(), tokenExpiration);
        String token = UUID.randomUUID().toString().replace("-", "");
        redisUtil.hset(CommonConstant.KET_USER, token, JSONUtils.toString(user));
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        //登录成功時，返回json格式进行提示
        ResponseUtil.sendMessage(response, new ApiResponse(true, ResponseEnum.LOGIN_SUCCESS, map));
    }

}

