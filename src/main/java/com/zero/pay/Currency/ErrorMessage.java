package com.zero.pay.Currency;

/**
 * 统一异常处理，返回统一异常数据结构
 */
public class ErrorMessage {
    public static final String SYSTEM_EXCEPTION = "系统繁忙，请稍后再试";
    public static final String NOT_LOGIN = "01001_您还未登陆或者登陆已超时，请重新登陆";
    public static final String MOBILE_ALREADY_REGISTER = "01002_该手机号已经被注册了";
    public static final String LOGIC_EXCEPTION = "01003_对不起，你是真的没有我帅";


    public static final String LOGIN_COOKIES_NAME="token";
    public static final String LOGIN_USERID_NAME="userId";
}
