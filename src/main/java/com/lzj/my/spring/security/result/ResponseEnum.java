package com.lzj.my.spring.security.result;

/**
 * @author Zijian Liao
 * @date 2020/1/9 9:37
 * @description
 */
public enum ResponseEnum {
    //200
    LOGIN_SUCCESS("200001", "登录成功"),
    OPERATION_SUCCESS("200001", "操作成功"),
    //300 业务系统类异常
    USER_NOT_FOUND("300001", "用户不存在"),
    OLD_PASSWORD_WRONG("300002", "旧密码不正确"),
    PARAMETER_WRONG("300003", "参数错误"),
    USER_IS_EXISTS("300004","用户已存在"),
    ROLE_IS_EXISTS("300005","角色已存在"),
    PERMISSION_EXISTS("300006","权限已存在"),
    PERMISSION_NOT_FOUND("300006","权限不存在"),
    //400为非业务异常
    LOGIN_FAIL("400001", "用户名或密码错误"),
    USER_DISABLED("400002","账号失效"),
    USER_LOCKED("400003","账号已锁定"),
    FORBIDDEN("400002", "用户暂无权限"),
    NOT_LOGIN("400003", "请登录"),
    WRONG_TOKEN("400004", "Token错误"),
    EXPIRATION_TOKEN("400005", "Token已到期,请重新登录"),
    OPERATION_FAIL("400006", "操作失败"),
    SYSTEM_EXCEPTION("400007","系统异常");

    private ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code;
    public String message;
}
