package com.lzj.my.spring.security.domain.in;

import javax.validation.constraints.NotNull;

/**
 * @author Zijian Liao
 * @date 2020/1/8 16:42
 * @description
 */
public class InPermission {

    private Integer id;
    private Integer pid;
    @NotNull(message = "权限名不能为空")
    private String name;
    private String icon;
    @NotNull(message = "权限类型不能为空")
    private Integer permissionType;
    @NotNull(message = "权限url不能为空")
    private String url;
    @NotNull(message = "权限状态不能为空")
    private Integer status;
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
