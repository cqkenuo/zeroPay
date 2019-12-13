package com.zero.pay.Controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zero.pay.Currency.CookieUtil;
import com.zero.pay.Currency.Enum.PayTypeEnum;
import com.zero.pay.Currency.ErrorMessage;
import com.zero.pay.Currency.RedisUtil;
import com.zero.pay.Entity.AccountCashierEntity;
import com.zero.pay.Entity.AccountEntity;
import com.zero.pay.Entity.CashierOrderLimitLogEntity;
import com.zero.pay.Entity.CashierQrcodeEntity;
import com.zero.pay.Form.InitCashierzichanForm;
import com.zero.pay.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class InitController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountCashierService cashierService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private InitService initService;
    @Autowired
    private CashierOrderLimitLogService orderLimitLogService;
    @Autowired
    private CashierQrcodeService qrcodeService;

    /**
     * 首页
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/indexdata")
    public String chshierData(HttpServletRequest request, Model model) {

        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();

//        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();



        if (userId != null) {
            AccountEntity accountEntity = accountService.getById(userId);
            if (accountEntity != null) {
                //判断该用户是否绑定的Google验证码 如果没有跳转到绑定验证码界面 先绑定验证码
                AccountCashierEntity accountCashierEntity = cashierService.getOne(Wrappers.<AccountCashierEntity>lambdaQuery().eq(AccountCashierEntity::getId, userId), false);
                if (accountCashierEntity.getSecretEnabled() == null||accountCashierEntity.getSecretEnabled() == false) {
                    return "/googleBinding";
                }
                if (accountEntity.getCategory().equals("CASHIER")) {
                    AccountCashierEntity one = cashierService.getOne(Wrappers.<AccountCashierEntity>lambdaQuery().eq(AccountCashierEntity::getId, accountEntity.getId()), false);
                    model.addAttribute("account", accountEntity);
                    model.addAttribute("cashier", one);
                    return "/index";
                }
            }
        }
        return "/login";
    }


    @RequestMapping("login")
    public String login() {
        return "/login";
    }

    @RequestMapping("demo")
    public String demo() {
        return "/demo";
    }

    @RequestMapping("/")
    public String init() {
        return "/login";
    }


    @RequestMapping("uploadQrcode")
    public String CashieruploadQrcode() {
        return "/cashier_qrcode";
    }

    @RequestMapping("/exchange")
    public String exchange(HttpServletRequest request) {
        return "/exchange";
    }

    @RequestMapping("/useramount")
    public String zichan(HttpServletRequest request, Model model) {
        try {
            String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();
            AccountEntity accountEntity = accountService.getById(userId);
            InitCashierzichanForm ziChanData = initService.getZiChanData(accountEntity.getId());
            model.addAttribute("ziChanData", ziChanData);
            return "/zichan";
        } catch (Exception e) {
            return "/login";
        }


    }


    @RequestMapping("/finCashierAll")
    public String finCashierAll(HttpServletRequest request, Model model) {
        try {
            String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();
            AccountEntity accountEntity = accountService.getById(userId);
            List<CashierOrderLimitLogEntity> limitLogEntities = orderLimitLogService.finCashierAll(accountEntity.getId());
            List<CashierOrderLimitLogEntity> entities = new ArrayList<>();
            for (CashierOrderLimitLogEntity entity : limitLogEntities
            ) {
                switch (entity.getCategory()) {
                    case "ORDER_PAID":
                        entity.setCategory("订单支付成功");
                        break;
                    case "INCREASE_ORDER_LIMIT":
                        entity.setCategory("代理增加收款人额度");
                        break;
                    case "APPLY_APPROVED":
                        entity.setCategory("提现申请单审核成功");
                        break;
                    case "MANUAL_APPLY":
                        entity.setCategory("平台手动收款");
                        break;
                }
                entities.add(entity);
            }
            model.addAttribute("entities", limitLogEntities);
            return "/flowdetails";
        } catch (Exception e) {
            return "/login";
        }
    }


    @RequestMapping("/erweima")
    public String erweima(HttpServletRequest request, Model model) {

        return "/erweima";
    }

    @RequestMapping("/squeakmanage")
    public String squeakManage(HttpServletRequest request, Model model) {

        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();

        List<CashierQrcodeEntity> qrcodeEntities = qrcodeService.list(Wrappers.<CashierQrcodeEntity>lambdaQuery().like(CashierQrcodeEntity::getCashierId, userId).eq(CashierQrcodeEntity::getType, PayTypeEnum.ALIPAYZKL));

//        request.getRequestDispatcher("/cashierQrcode").forward(request, response);
        model.addAttribute("qrcodeEntities", qrcodeEntities);


        return "/squeakmanage";
    }

    @RequestMapping("/squeakAdd")
    public String squeakUpdate(HttpServletRequest request, Model model) {

        return "/squeakAdd";
    }

    @RequestMapping("/cahiserQrcodeUpdate")
    public String cahiserQrcodeUpdate(HttpServletRequest request, Model model) {

        return "/cahiserQrcodeUpdate";
    }


    @RequestMapping("/qiangdan")
    public String qdgame(HttpServletRequest request, Model model) {
        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();
        AccountCashierEntity byId = cashierService.getById(userId);
        Map<String,String> user=new HashMap<String,String>();
        if (byId!=null){
            user.put("MaxMonery",byId.getOrderLimit().toString());
            user.put("commissionRate",byId.getCommissionRate().toString());
            model.addAttribute("user",user);

            model.addAllAttributes(user);
        }
        return "/qdgame";
    }


}
