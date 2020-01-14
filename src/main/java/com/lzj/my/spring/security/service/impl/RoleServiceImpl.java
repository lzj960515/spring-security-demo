package com.lzj.my.spring.security.service.impl;

import com.lzj.my.spring.security.mapper.RoleMapper;
import com.lzj.my.spring.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zijian Liao
 * @date 2020/1/5
 * @description:
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


}
