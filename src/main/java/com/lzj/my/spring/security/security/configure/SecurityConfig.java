package com.lzj.my.spring.security.security.configure;

import com.lzj.my.spring.security.security.filter.PermissionVoter;
import com.lzj.my.spring.security.security.filter.TokenLoginFilter;
import com.lzj.my.spring.security.security.filter.TokenVerifyFilter;
import com.lzj.my.spring.security.security.handle.MyAccessDeniedHandler;
import com.lzj.my.spring.security.security.handle.MyAuthenticationEntryPoint;
import com.lzj.my.spring.security.security.properties.RsaKeyProperties;
import com.lzj.my.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Zijian Liao
 * @date 2020/1/3 16:37
 * @description
 */
@Configuration
@EnableWebSecurity
//开启权限控制注解支持 jsr250-annotations jsr250-api的注解,pre-post-annotations spring表达式注解 secured-annotations SpringSecurity提供的注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MyAccessDeniedHandler myAccessDeniedHandler(){
        return new MyAccessDeniedHandler();
    }

    @Bean
    public MyAuthenticationEntryPoint myAuthenticationEntryPoint(){
        return new MyAuthenticationEntryPoint();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                //关闭跨站请求防护
                .disable()
                .authorizeRequests()
                //自定义accessDecisionManager
                .accessDecisionManager(accessDecisionManager())
                //允许不登陆就可以访问的方法，多个用逗号分隔
                .antMatchers("/public/**").permitAll()
                //基础角色USER可访问所有
                //.antMatchers("/**").hasAnyAuthority("USER")
                //其他的需要授权后访问
                .anyRequest().authenticated()
                .and()
                //增加自定义认证过滤器
                .addFilterAt(tokenLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                //增加自定义验证认证过滤器
                .addFilter(tokenVerifyFilter())
                //禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //增加自定义异常
        http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint()).accessDeniedHandler(myAccessDeniedHandler());

    }

    @Bean
    public TokenVerifyFilter tokenVerifyFilter() throws Exception {
        return new TokenVerifyFilter(super.authenticationManager());
    }

    //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    TokenLoginFilter tokenLoginFilter() throws Exception {
        TokenLoginFilter filter = new TokenLoginFilter(super.authenticationManager());
        //指定拦截的url 即登录地址
        filter.setFilterProcessesUrl("/system/login");
        //重用WebSecurityConfigurerAdapter配置的AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                new WebExpressionVoter(),
                permissionVoter());
        return new UnanimousBased(decisionVoters);
    }

    @Bean
    public PermissionVoter permissionVoter(){
        return new PermissionVoter();
    }

}
