package com.lzj.my.spring.security.service;

import com.lzj.my.spring.security.domain.Permission;
import com.lzj.my.spring.security.domain.in.InPermission;

import java.util.List;

/**
 * @author Zijian Liao
 * @date 2020/1/8 16:40
 * @description 权限接口
 */
public interface PermissionService {

    /**
     * 查询权限列表
     *
     * @param in 权限信息 {@link InPermission}
     * @return
     */
    List<Permission> listPermission(InPermission in);

    /**
     * 根据用户名查询权限列表
     *
     * @param username 用户名
     * @return
     */
    List<Permission> selectPermissionsByUsername(String username);

    /**
     * 根据roleId查询权限url
     * @param roleIds 角色id列表
     * @return
     */
    List<String> selectUrlByRoleIds(List<Integer> roleIds);
}
