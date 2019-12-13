package com.zero.pay.Controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zero.pay.Currency.*;
import com.zero.pay.Currency.Enum.PayTypeEnum;
import com.zero.pay.Entity.CashierQrcodeEntity;
import com.zero.pay.Service.CashierQrcodeService;
import com.zero.pay.Service.SqueakManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Create  by wgh on 2019/12/4 21:45.
 */
@Controller
@RequestMapping("/squeak")
public class SqueakManageController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SqueakManageService squeakManageService;


    @PostMapping("/addSqueak")
    @ResponseBody
    public Result addSqueak(HttpServletRequest request, @Valid String zhikouling, @Valid String uname, @Valid String skaccount) {
        CashierQrcodeEntity cashierQrcodeEntity = new CashierQrcodeEntity();
        //获取id值
        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();

        //上传字段
        Snowflake sf = IdUtil.createSnowflake(1, 1);
        String qrCodeId = "Q"+sf.nextId();
        cashierQrcodeEntity.setId(qrCodeId);
        cashierQrcodeEntity.setCashierId(userId);
        cashierQrcodeEntity.setImgQrcode(PayTypeEnum.ALIPAYZKL.toString());
        cashierQrcodeEntity.setImgUrl(zhikouling);
        cashierQrcodeEntity.setImgUsername(uname);
        cashierQrcodeEntity.setImgPhone(skaccount);
        cashierQrcodeEntity.setEnabled(false);
        cashierQrcodeEntity.setCreatimeTime(LocalDateTime.now());
        cashierQrcodeEntity.setType(PayTypeEnum.ALIPAYZKL.toString());

        boolean b = squeakManageService.saveSqueak(cashierQrcodeEntity);
        return Result.success("success", JsonUitls.objectToJson(b));
    }

    @GetMapping("/qryInfo")
    public String qryInfo(HttpServletRequest request, @Valid String id , @Valid Boolean flag , Model model) {

        //根据id查询吱口令详细信息
        CashierQrcodeEntity cashierQrcodeEntity = squeakManageService.qryInfo(id);
        model.addAttribute("cashierQrcodeEntity" , cashierQrcodeEntity);
        model.addAttribute("flag" , flag);
        return "squeakData";
    }

    @PostMapping("/delInfo")
    @ResponseBody
    public Result delInfo(@Valid String id){
        Boolean b = squeakManageService.removeById(id);
        return b == true ? Result.success("success",JsonUitls.objectToJson(b)) : Result.error();
    }

}
