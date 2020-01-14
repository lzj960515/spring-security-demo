package com.lzj.my.spring.security.result;

import java.util.Date;

/**
 * @author Zijian Liao
 * @date 2020/1/3 16:49
 * @description 载荷对象 为了方便后期获取token中的用户信息，将token中载荷部分单独封装成一个对象
 */
public class Payload<T> {
    private String id;
    private T userInfo;
    private Date expiration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(T userInfo) {
        this.userInfo = userInfo;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
