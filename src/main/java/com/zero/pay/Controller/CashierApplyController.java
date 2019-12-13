package com.zero.pay.Controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zero.pay.Currency.CookieUtil;
import com.zero.pay.Currency.DateUtil;
import com.zero.pay.Currency.ErrorMessage;
import com.zero.pay.Currency.Result;
import com.zero.pay.Entity.CashierApplyEntity;
import com.zero.pay.Entity.ShopWithdrawalEntity;
import com.zero.pay.Service.CashierApplyService;
import com.zero.pay.Service.ShopWithdrawalService;
import com.zero.pay.Util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 收款人申请单 前端控制器
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@Controller
@RequestMapping("/cashierApply")
public class CashierApplyController {

    @Autowired
    private CashierApplyService cashierApplyService;
    @Autowired
    private ShopWithdrawalService shopWithdrawalService;

    @PostMapping("/saveRemittance")
    @ResponseBody
    public Result saveRemittance(@Valid String shopWithdrawalId, @Valid String cashierId, @Valid BigDecimal amount) {
        boolean b = cashierApplyService.saveRemittance(shopWithdrawalId, cashierId, amount);
        return b == true ? Result.success() : Result.error();
    }


    @RequestMapping({"/withdrawal/{uniqueNo}/upload"})
    public String withdrawalupload(HttpServletRequest request, Model model, @PathVariable("uniqueNo") String uniqueNo) {

        CashierApplyEntity one = cashierApplyService.getOne(Wrappers.<CashierApplyEntity>lambdaQuery().eq(CashierApplyEntity::getUniqueNo, uniqueNo), false);

        ShopWithdrawalEntity Shopentity = shopWithdrawalService.getOne(Wrappers.<ShopWithdrawalEntity>lambdaQuery().eq(ShopWithdrawalEntity::getUniqueNo, one.getShopWithdrawalUniqueNo()), false);
//        申请状态。CREATED：已申请；WAITING：待审核；DENIED：未通过；APPROVED：已通过；TIMEOUT：超时；CANCELED：已取消

        this.setResultStatus(one);

        one.setImage(FtpUtil.formatFtpUrl(one.getImage()));

        model.addAttribute("one", one);
        model.addAttribute("bankAccountName", Shopentity.getBankAccountName());
        model.addAttribute("bankAccountNo", Shopentity.getBankAccountNo());
        model.addAttribute("bankName", Shopentity.getBankName());

        return "/withdrawalupload";
    }


    @RequestMapping({"/submitImages"})
    @ResponseBody
    public Result submitImages(@Param("image_url") String image_url, @Param("id") String id) {
        if(image_url==null||"".equals(image_url)){
            return Result.error("找不到图片");
        }

        CashierApplyEntity cashierApplyEntity = cashierApplyService.getOne(Wrappers.<CashierApplyEntity>lambdaQuery().eq(CashierApplyEntity::getId, id), false);
        cashierApplyEntity.setImage(image_url);
        cashierApplyEntity.setApproveTime(DateUtil.getDateTime());
        cashierApplyEntity.setStatus("WAITING");
        boolean b = cashierApplyService.saveOrUpdate(cashierApplyEntity);
        return b == true ? Result.success() : Result.error();
    }

    @RequestMapping("/withdrawalAll")
    public String withdrawalAll(HttpServletRequest request, Model model) {
        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();
        List<CashierApplyEntity> entityList = cashierApplyService.list(Wrappers.<CashierApplyEntity>lambdaQuery().eq(CashierApplyEntity::getCashierId, userId).orderByDesc(CashierApplyEntity::getCreateTime));
        for (CashierApplyEntity one : entityList) {
            this.setResultStatus(one);
            //取image的时候转成完整链接
            one.setImage(FtpUtil.formatFtpUrl(one.getImage()));
        }
        model.addAttribute("one", entityList);
        return "/withdrawalAll";
    }

    private void setResultStatus(CashierApplyEntity one) {
        switch (one.getStatus()) {
            case "CREATED":
                one.setStatus("已申请");
                break;
            case "WAITING":
                one.setStatus("待审核");
                break;
            case "DENIED":
                one.setStatus("未通过");
                break;
            case "APPROVED":
                one.setStatus("已通过");
                break;
            case "TIMEOUT":
                one.setStatus("超时");
                break;
            case "CANCELED":
                one.setStatus("已取消");
                break;
        }
    }


}

