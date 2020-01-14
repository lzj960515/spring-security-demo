package com.lzj.my.spring.security.mapper;

import com.lzj.my.spring.security.domain.Permission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
 * @author Zijian Liao
 * @date 2020/1/8 16:40
 * @description
 */
public interface PermissionMapper extends MyMapper<Permission> {
    List<Permission> selectPermissionsByUsername(String username);

    List<String> selectUrlByRoleIds(@Param("roleIds") List<Integer> roleIds);
}