package com.lzj.my.spring.security.service;

import com.lzj.my.spring.security.domain.in.InUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Zijian Liao
 * @date 2020/1/5
 * @description:
 */
public interface UserService extends UserDetailsService {

    /**
     * 新增用户
     *
     * @param in 用户信息 {@link InUser}
     * @return
     */
    int insertUser(InUser in);
}
