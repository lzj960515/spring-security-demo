package com.lzj.my.spring.security.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzj.my.spring.security.comstant.CommonConstant;
import com.lzj.my.spring.security.domain.Permission;
import com.lzj.my.spring.security.domain.in.InPermission;
import com.lzj.my.spring.security.mapper.PermissionMapper;
import com.lzj.my.spring.security.result.ResponseEnum;
import com.lzj.my.spring.security.service.PermissionService;
import com.lzj.my.spring.security.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Zijian Liao
 * @date 2020/1/8 16:40
 * @description
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> listPermission(InPermission in) {
        Example example = new Example(Permission.class);
        Example.Criteria criteria = example.createCriteria();
        if (in.getName() != null) {
            criteria.andEqualTo("name", in.getName());
        }
        if(in.getPermissionType()!=null){
            criteria.andEqualTo("permissionType",in.getPermissionType());
        }
        if(in.getStatus()!=null){
            criteria.andEqualTo("status",in.getStatus());
        }
        return permissionMapper.selectByExample(example);
    }



    @Override
    public List<Permission> selectPermissionsByUsername(String username) {
        return permissionMapper.selectPermissionsByUsername(username);
    }

    @Override
    public List<String> selectUrlByRoleIds(List<Integer> roleIds) {
        return permissionMapper.selectUrlByRoleIds(roleIds);
    }

}
