package com.lzj.my.spring.security.service.impl;

import com.lzj.my.spring.security.domain.User;
import com.lzj.my.spring.security.domain.in.InUser;
import com.lzj.my.spring.security.mapper.UserMapper;
import com.lzj.my.spring.security.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Zijian Liao
 * @date 2020/1/5
 * @description:
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.findByName(username);
    }


    @Override
    public int insertUser(InUser in) {
        //用户是否存在
        com.lzj.my.spring.security.domain.User user = new User();
        BeanUtils.copyProperties(in, user);
        user.setPassword(passwordEncoder.encode(in.getPassword()));
        user.setStatus(1);
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        user.setLastChangePasswordTime(date);
        user.setLastLoginTime(date);
        return userMapper.insert(user);
    }
}