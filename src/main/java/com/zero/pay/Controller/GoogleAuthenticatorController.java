package com.zero.pay.Controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zero.pay.Currency.*;
import com.zero.pay.Entity.AccountCashierEntity;
import com.zero.pay.Entity.AccountEntity;
import com.zero.pay.Service.AccountCashierService;
import com.zero.pay.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/google")
public class GoogleAuthenticatorController {

    @Autowired
    private AccountCashierService accountCashierService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RedisUtil redisUtil;


    @PostMapping("/qrcode")
    @ResponseBody
    public Result genSecret(HttpServletRequest request) {
        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();

        //获取登录用户信息
        AccountEntity accountEntity = accountService.getOne(Wrappers.<AccountEntity>lambdaQuery().eq(AccountEntity::getId, userId), false);

        String secret;
        //根据id查询密钥是否存在
        AccountCashierEntity accountCashierEntity = accountCashierService.qrySecret(accountEntity.getId());

        //如果密钥为空或null，生成密钥,并将密钥保存到库中
        if (null == accountCashierEntity.getSecret() || accountCashierEntity.getSecret().isEmpty()) {
            secret = GoogleAuthenticator.generateSecretKey();
            accountCashierService.updSecret(accountCashierEntity.getId(), secret);
        } else {
//            return Result.error("已绑定Google验证器，如需解绑请联系客服人员！");
            secret = accountCashierEntity.getSecret();
        }

        //生成绑定二维码
        String qrcode = GoogleAuthenticator.getQRBarcode(accountEntity.getUsername(), secret);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("url", qrcode);
        resultMap.put("name", accountEntity.getUsername());
        resultMap.put("key", secret);
        return Result.success("success", JsonUitls.objectToJson(resultMap));
    }

    @PostMapping("/verifyCode")
    @ResponseBody
    public Result verifyCode(HttpServletRequest request, @Valid Integer code) {
        //获取登录用户信息
        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();

        //获取登录用户信息
        AccountEntity accountEntity = accountService.getOne(Wrappers.<AccountEntity>lambdaQuery().eq(AccountEntity::getId, userId), false);

        //根据id查询密钥是否存在
        AccountCashierEntity accountCashierEntity = accountCashierService.qrySecret(accountEntity.getId());
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        boolean b = ga.check_code(accountCashierEntity.getSecret(), code, t);
        if (b == true) {
            accountCashierEntity.setSecretEnabled(true);
            accountCashierService.updateById(accountCashierEntity);
            return Result.success();
        } else {
            return Result.error();
        }
    }

}
