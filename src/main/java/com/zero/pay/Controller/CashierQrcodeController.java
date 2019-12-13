package com.zero.pay.Controller;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zero.pay.Currency.*;
import com.zero.pay.Currency.Enum.PayTypeEnum;
import com.zero.pay.Entity.AccountCashierEntity;
import com.zero.pay.Entity.CashierQrcodeEntity;
import com.zero.pay.Service.AccountCashierService;
import com.zero.pay.Service.CashierQrcodeService;
import com.zero.pay.Util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@Controller
@RequestMapping("/cashierQrcode")
public class CashierQrcodeController {

    @Value("${storage-path}")
    private String path;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CashierQrcodeService qrcodeService;
    @Autowired
    private AccountCashierService AccountCashierService;

    @RequestMapping("/upload")
    @ResponseBody
    public Result cashierUploadQrcode(HttpServletRequest request, @RequestParam(name = "file") MultipartFile multipartFile, @Valid final CashierQrcodeEntity qrcodeEntity) {
        //获取id值
        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();
        Result result = null;
        try {
            //使用FTP存储图片或其他静态资源
            String img_url = FtpUtil.uploadFile(multipartFile, userId);
            System.out.println(img_url);//得到的存储到数据库的"图片"路径
            //使用Ngnix获取完整图片
            String full_img_url = FtpUtil.formatFtpUrl(img_url);
            System.out.println(full_img_url);
            result = Result.success();
            JSONObject obj = new JSONObject();
            obj.put("img_url",img_url);
            obj.put("full_img_url",full_img_url);
            result.setResult(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/insertInfo")
    @ResponseBody
    public Result insertInfo(HttpServletRequest request,  @Valid String icon, @Valid Integer ewmclass, @Valid String uname, @Valid String skaccount,@Valid Long code) {


        //获取id值
        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();
        //通过用户id获取密钥
        AccountCashierEntity accountCashierEntity = AccountCashierService.getOne(Wrappers.<AccountCashierEntity>lambdaQuery().eq(AccountCashierEntity::getId, userId), false);
        //验证 google是否匹配
        Boolean verifyCode = GoogleUtils.verifyCode(accountCashierEntity.getSecret(), code);
        if (verifyCode==false){
            return Result.error("Google验证码错误");
        }

        //上传字段
        CashierQrcodeEntity cashierQrcodeEntity = new CashierQrcodeEntity();
        Snowflake sf = IdUtil.createSnowflake(1, 1);
        String qrCodeId = "Q"+sf.nextId();
        cashierQrcodeEntity.setId(qrCodeId);
        cashierQrcodeEntity.setImgUrl(icon);
        cashierQrcodeEntity.setCashierId(userId);
        //cashierQrcodeEntity.setImgQrcode(icon);
        cashierQrcodeEntity.setImgUsername(uname);
        cashierQrcodeEntity.setImgPhone(skaccount);
        cashierQrcodeEntity.setEnabled(false);
        cashierQrcodeEntity.setCreatimeTime(LocalDateTime.now());
        switch (ewmclass) {
            case 1:
                cashierQrcodeEntity.setType(PayTypeEnum.WECHAT.toString());
                break;
            case 2:
                cashierQrcodeEntity.setType(PayTypeEnum.ALIPAY.toString());
                break;
            case 3:
                cashierQrcodeEntity.setType(PayTypeEnum.MERGEPAY.toString());
                break;
            case 4:
                cashierQrcodeEntity.setType(PayTypeEnum.UNIONPAY.toString());
                break;
        }

        boolean b = qrcodeService.saveQrcode(cashierQrcodeEntity);
        return b == true ? Result.success("success", JsonUitls.objectToJson(b)) : Result.error();
    }

    @PostMapping("/updateQrcodeStatus")
    @ResponseBody
    public Result updateQrcodeStatus(HttpServletRequest request, @Valid String id) {
        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();
        boolean b = qrcodeService.updateQrcodeStatus(userId, id);
        Integer enabled = 0;
        return b == true ? Result.success(JsonUitls.objectToJson(enabled)) : Result.error();
    }


    @RequestMapping("/qordeCashierType/{type}")
    public String qordeCashierType(HttpServletRequest request, @PathVariable("type") String type, Model model) throws ServletException, IOException {


        String userId = CookieUtil.get(request, ErrorMessage.LOGIN_USERID_NAME).getValue();

        List<CashierQrcodeEntity> qrcodeEntities = qrcodeService.list(Wrappers.<CashierQrcodeEntity>lambdaQuery().like(CashierQrcodeEntity::getCashierId, userId).eq(CashierQrcodeEntity::getType, type));

//        request.getRequestDispatcher("/cashierQrcode").forward(request, response);
        model.addAttribute("qrcodeEntities", qrcodeEntities);
        model.addAttribute("type", type);


        return "cashierQrcode";
    }


}

