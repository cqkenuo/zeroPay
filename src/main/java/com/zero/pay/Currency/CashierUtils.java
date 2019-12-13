package com.zero.pay.Currency;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CashierUtils {

    @Autowired
    private static RedisUtil redisUtil;
    /**
     * 获取该cashier是否在线状态
     * @param cashierId
     * @return Boolean
     */
    public static Boolean getCashierLineStatus(String cashierId) {
        redisUtil=new RedisUtil();
        List<String> heartbeat = redisUtil.getListCache("heartbeat", String.class);
        boolean status=false;
        if (heartbeat == null) {
            return status;
        }
        for (int i = 0; i < heartbeat.size(); i++) {
            if (heartbeat.get(i).equals(cashierId)){
                status=true;
                return status;
            }
        }
        return status;
    }



    public String getUserId(HttpServletRequest request){
        Cookie cookie = CookieUtil.get(request, ErrorMessage.LOGIN_COOKIES_NAME);
        if (cookie==null){
            throw LogicException.le("cookies获取失败 请重新登陆");
        }
        String cookieValue = cookie.getValue();
        String userId = redisUtil.hGet(cookieValue, "userId").toString();
        if (userId==null){
            throw LogicException.le("cookies获取失败 请重新登陆");

        }
        return   redisUtil.hGet(CookieUtil.get(request, ErrorMessage.LOGIN_COOKIES_NAME).getValue(), "userId").toString();
    }
}
