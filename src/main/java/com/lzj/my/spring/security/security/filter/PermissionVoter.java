package com.lzj.my.spring.security.security.filter;

import com.lzj.my.spring.security.domain.Permission;
import com.lzj.my.spring.security.domain.Role;
import com.lzj.my.spring.security.domain.in.InPermission;
import com.lzj.my.spring.security.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zijian Liao
 * @date 2020/1/7 16:08
 * @description 自定义权限过滤器
 */
public class PermissionVoter implements AccessDecisionVoter<FilterInvocation> {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    RequestMappingHandlerMapping handlerMapping;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
        if (authentication == null) {
            return ACCESS_DENIED;
        }
        HttpServletRequest request = object.getRequest();
        try {
            handlerMapping.getHandler(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String urlPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        //获取所有权限
        InPermission inPermission = new InPermission();
        inPermission.setPermissionType(3);
        inPermission.setStatus(1);
        List<Permission> permissions = permissionService.listPermission(inPermission);
        List<String> allUrl = permissions.stream().map(Permission::getUrl).collect(Collectors.toList());
        //请求url是否存在权限列表中
        if (allUrl.contains(urlPattern)) {
            List<Role> roles = (List<Role>) authentication.getAuthorities();//Role(id=1, roleName=USER, roleDesc=用户)
            List<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
            List<String> urls = permissionService.selectUrlByRoleIds(roleIds);
            if (urls.contains(urlPattern)) return ACCESS_GRANTED;
            return ACCESS_DENIED;
        }
        return ACCESS_ABSTAIN;
    }
}
