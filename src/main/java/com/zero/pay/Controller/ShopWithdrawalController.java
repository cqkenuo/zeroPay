package com.zero.pay.Controller;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zero.pay.Currency.*;
import com.zero.pay.Entity.*;
import com.zero.pay.Service.*;
import com.zero.pay.Util.Constants;
import com.zero.pay.Util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商家提现单 前端控制器
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@Controller
@RequestMapping("/shopWithdrawalEntity")
public class ShopWithdrawalController {

    @Autowired
    private ShopWithdrawalService shopWithdrawalService;
    @Autowired
    private CashierApplyService cashierApplyService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountCashierService accountCashierService;
    @Autowired
    private AccountAgentService accountAgentService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/info")
    @ResponseBody
    public Result qryShopWithdrawal(@Valid String id) {
        List<ShopWithdrawalEntity> shopWithdrawalEntityList = shopWithdrawalService.qryShopWithDrawal(id);
        return Result.success("success", JsonUitls.objectToJson(shopWithdrawalEntityList));
    }

    @RequestMapping("/listshopWithdrawalAvailable")
    public String listshopWithdrawalAvailable(HttpServletRequest request, Model model, @Valid String id) {
        List<ShopWithdrawalEntity> shopWithdrawalEntityList = shopWithdrawalService.listshopWithdrawalAvailable();
        ArrayList<String> status = new ArrayList<>();
        status.add("DENIED");
        status.add("TIMEOUT");
        status.add("CANCELED");
        for (ShopWithdrawalEntity swEntity : shopWithdrawalEntityList) {
            swEntity.setBalanceBefore(new BigDecimal(0));
            swEntity.setBalanceAfter(swEntity.getAmount());
            List<CashierApplyEntity> cashierApplyEntityList = cashierApplyService.list(Wrappers.<CashierApplyEntity>lambdaQuery().eq(CashierApplyEntity::getShopWithdrawalUniqueNo, swEntity.getUniqueNo()).notIn(CashierApplyEntity::getStatus, status));
            for (CashierApplyEntity caEntity : cashierApplyEntityList) {
                //扣除余额
                swEntity.setBalanceBefore(swEntity.getBalanceBefore().add(caEntity.getAmount()));
                swEntity.setBalanceAfter(swEntity.getBalanceAfter().subtract(caEntity.getAmount()));
            }
//            CREATED：提现中；FINISHED：已提现

            switch (swEntity.getStatus()) {
                case "CREATED":
                    swEntity.setStatus("提现中");
                    break;
                case "FINISHED":
                    swEntity.setStatus("已提现");
                    break;
            }

        }
        model.addAttribute("orderwaiting", shopWithdrawalEntityList);
        return "/withdrawal";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Result cashierUploadWithdrawal(HttpServletRequest request, @RequestParam(name = "file") MultipartFile multipartFile) {
        //获取id值
        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();
        Result result = null;
        try {
            CashierApplyEntity one = cashierApplyService.getOne(Wrappers.<CashierApplyEntity>lambdaQuery().eq(CashierApplyEntity::getCashierId, userId).eq(CashierApplyEntity::getStatus, "CREATED"), false);
            if (one == null) {
                return Result.error("不需要再上传图片了");
            }
            //使用FTP存储图片或其他静态资源
            String img_url = FtpUtil.uploadFile(multipartFile, userId);
            System.out.println(img_url);//得到的存储到数据库的"图片"路径
            //使用Ngnix获取完整图片
            String full_img_url = FtpUtil.formatFtpUrl(img_url);
            System.out.println(full_img_url);
            result = Result.success();
            JSONObject obj = new JSONObject();
            obj.put("img_url", img_url);
            obj.put("full_img_url", full_img_url);
            result.setResult(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/addCashierApply")
    @ResponseBody
    public Result addCashierApply(HttpServletRequest request, Model model, @Valid String id, @Param("unique_no") String unique_no, @Param("amount") BigDecimal amount) {
        //判断金额是否满足
        ShopWithdrawalEntity sw = shopWithdrawalService.getOne(Wrappers.<ShopWithdrawalEntity>lambdaQuery().eq(ShopWithdrawalEntity::getUniqueNo, unique_no));
        //新增
        CashierApplyEntity applyEntity = new CashierApplyEntity();
        Snowflake sf = IdUtil.createSnowflake(1, 1);
        String cashierId = "CA" + sf.nextId();
        applyEntity.setId(cashierId);
        String cashier_unique_no = unique_no + Constants.randomStrGenerate(2);
        applyEntity.setUniqueNo(cashier_unique_no);
        applyEntity.setShopWithdrawalId(sw.getId());
        applyEntity.setShopWithdrawalUniqueNo(unique_no);
        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();
        AccountEntity accountEntity = accountService.getOne(Wrappers.<AccountEntity>lambdaQuery().eq(AccountEntity::getId, userId), false);
        AccountCashierEntity accountCashierEntity = accountCashierService.getOne(Wrappers.<AccountCashierEntity>lambdaQuery().eq(AccountCashierEntity::getId, userId), false);
        applyEntity.setCashierId(userId);
        applyEntity.setCashierName(accountEntity.getName());
        applyEntity.setCashierUsername(accountEntity.getUsername());
        applyEntity.setAmount(amount);
        applyEntity.setCommissionRate(accountCashierEntity.getCommissionRate());
        applyEntity.setAgentId(accountCashierEntity.getAgentId());
        AccountAgentEntity accountAgentEntity = accountAgentService.getOne(Wrappers.<AccountAgentEntity>lambdaQuery().eq(AccountAgentEntity::getId, accountCashierEntity.getAgentId()), false);
        applyEntity.setAgentCommissionRate(accountAgentEntity.getCommissionRate());
        applyEntity.setStatus("CREATED");
        applyEntity.setImage("");
        applyEntity.setApproveTime("");
        applyEntity.setCreateTime(DateUtil.getDateTime());

        boolean bl = cashierApplyService.addCashierApply(applyEntity);
        if (bl) {
            Map resultMap = new HashMap();
            resultMap.put("unique_no", cashier_unique_no);
            return Result.success(JsonUitls.objectToJson(resultMap));
        } else {
            return Result.error("申请失败");
        }
    }
}

