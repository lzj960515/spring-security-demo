package com.lzj.my.spring.security.mapper;

import com.lzj.my.spring.security.domain.Role;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {

    @Select("SELECT r.id, r.role_name roleName, r.role_desc roleDesc " +
            "FROM t_role r, t_user_role ur " +
            "WHERE r.id=ur.role_id AND ur.user_id=#{uid}")
    List<Role> findByUid(Integer uid);
}