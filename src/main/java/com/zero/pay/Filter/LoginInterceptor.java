package com.zero.pay.Filter;

import com.zero.pay.Currency.CookieUtil;
import com.zero.pay.Currency.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("LoginInterceptor");
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        Cookie tokenCookie = CookieUtil.get(request, "token");
        Cookie userCookie = CookieUtil.get(request, "userId");
        if (tokenCookie != null && userCookie != null) {
            String token = tokenCookie.getValue();
            String userId = userCookie.getValue();
            if (token != null && userId != null) {
                if (token.equals(redisUtil.hGet(userId, "token"))) {
                    return true;
                }
            }
        }
        response.sendRedirect("/login");
        //request.getRequestDispatcher("/login").forward(request,response);
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
