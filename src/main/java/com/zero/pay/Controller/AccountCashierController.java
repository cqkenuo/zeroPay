package com.zero.pay.Controller;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zero.pay.Currency.*;
import com.zero.pay.Entity.AccountEntity;
import com.zero.pay.Form.AccountCashierLoginForm;
import com.zero.pay.Service.AccountCashierService;
import com.zero.pay.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 收款人基本信息 前端控制器
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-28
 */
@Controller
@RequestMapping("/api/cashier")
public class AccountCashierController {
    // Redis
    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    @Autowired
    private AccountCashierService cashierService;

    @Autowired
    private AccountService accountService;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/loginData")
    @ResponseBody
    public Result loginData(@Valid final AccountCashierLoginForm loginForm, HttpServletResponse response) {

        AccountEntity one = accountService.getOne(Wrappers.<AccountEntity>lambdaQuery().eq(AccountEntity::getUsername, loginForm.getLoginName()).eq(AccountEntity::getPasswordHash, MD5Utils.stringToMD5(loginForm.getPassword())), false);
        if (one != null) {
            HashOperations<String, String, String> ho = redisTemplate.opsForHash();
            Snowflake snowflake = IdUtil.createSnowflake(1, 1);
            long id = snowflake.nextId();
            String token = "A" + id;
            redisUtil.hPut(one.getId(), "token", token);
            //移除掉所有值为该userId的数据
            CookieUtil.set(response, "token", token, 1800);
            CookieUtil.set(response, "userId", one.getId(), 1800);
            return Result.success("success", null);
        } else {
            return Result.error();
        }
    }

    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = CookieUtil.get(request, "userId").getValue();
        redisUtil.hDelete(userId,"token","userId");
        response.sendRedirect("/login");
        return "/login";
    }

    /**
     * 开启收款 关闭收款方法
     *
     * @param request
     * @param issuccess
     * @return
     */
    @PostMapping("/heartbeat")
    @ResponseBody
    public Result heartbeat(HttpServletRequest request, @Valid Boolean issuccess) {
        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();
//        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();
        List<String> heartbeat = redisUtil.getListCache("heartbeat", String.class);
        if (heartbeat == null) {
            heartbeat = new ArrayList<>();
            heartbeat.add(userId);
        }
        //启动收款
        if (issuccess == true) {

            //判断收款缓存是否已经存在该用户，如果有删除
            for (int i = 0; i < heartbeat.size(); i++) {
                if (heartbeat.get(i).equals(userId))
                    heartbeat.remove(i);
            }
            //开始写到在线收款缓存
            heartbeat.add(userId);
        } else {
            //用户取消在线收款，那么将他从收款缓存中移除
            for (int i = 0; i < heartbeat.size(); i++) {
                if (heartbeat.get(i).equals(userId))
                    heartbeat.remove(i);
            }
        }

        //重新写到在线收款表里
        if (heartbeat.size() <= 0) {
            heartbeat.add("zhanweifu");
        }
        redisUtil.putListCacheWithExpireTime("heartbeat", heartbeat, 3600);

        return Result.success();
    }

}

