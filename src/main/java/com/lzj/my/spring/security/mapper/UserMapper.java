package com.lzj.my.spring.security.mapper;

import com.lzj.my.spring.security.domain.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface UserMapper extends MyMapper<User> {

    @Select("select * from t_user where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.lzj.my.spring.security.mapper.RoleMapper.findByUid"))
    })
    User findByName(String username);
}