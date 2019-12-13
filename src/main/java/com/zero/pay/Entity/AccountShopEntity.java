package com.zero.pay.Entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 商家基本信息
 * </p>
 *
 * @author 麒麟
 * @since 2019-12-03
 */
@TableName("account_shop")
@Data
public class AccountShopEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID，即account表的id字段
     */
    private String id;

    /**
     * 余额，单位：元
     */
    private BigDecimal balance;

    /**
     * 提现手续费率，例如：0.002
     */
    private BigDecimal withdrawalChargeRate;

    /**
     * 提现密码
     */
    private String withdrawalPassword;

    /**
     * 商家接口的密钥
     */
    private String apiSignKey;

    /**
     * 商家接口订单回调地址
     */
    private String apiOrderUrl;

    /**
     * 支付宝每日订单额度，单位：元
     */
    private BigDecimal dailyOrderLimitAlipay;

    /**
     * 微信每日订单额度，单位：元
     */
    private BigDecimal dailyOrderLimitWechat;


    @Override
    public String toString() {
        return "AccountShopEntity{" +
        "id=" + id +
        ", balance=" + balance +
        ", withdrawalChargeRate=" + withdrawalChargeRate +
        ", withdrawalPassword=" + withdrawalPassword +
        ", apiSignKey=" + apiSignKey +
        ", apiOrderUrl=" + apiOrderUrl +
        ", dailyOrderLimitAlipay=" + dailyOrderLimitAlipay +
        ", dailyOrderLimitWechat=" + dailyOrderLimitWechat +
        "}";
    }
}
