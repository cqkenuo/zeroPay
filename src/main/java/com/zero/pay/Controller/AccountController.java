package com.zero.pay.Controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zero.pay.Currency.Enum.CategoryEnum;
import com.zero.pay.Currency.JsonUitls;
import com.zero.pay.Currency.MD5Utils;
import com.zero.pay.Currency.Result;
import com.zero.pay.Entity.AccountEntity;
import com.zero.pay.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * <p>
 * 账号 前端控制器
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-28
 */
@Controller
@RequestMapping("/accountEntity")
public class AccountController {


}

