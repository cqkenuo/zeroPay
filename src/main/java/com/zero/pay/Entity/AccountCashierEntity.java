package com.zero.pay.Entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 收款人基本信息
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-28
 */
@TableName("account_cashier")
@Data
public class AccountCashierEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID，即account表的id字段
     */
    private String id;

    /**
     * 收款费率，例如：0.002
     */
    private BigDecimal commissionRate;

    /**
     * 剩余收款额度，单位：元
     */
    private BigDecimal orderLimit;

    /**
     * 支付宝每日收款额度，单位：元
     */
    private BigDecimal dailyOrderLimitAlipay;

    /**
     * 微信每日收款额度，单位：元
     */
    private BigDecimal dailyOrderLimitWechat;

    /**
     * 代理账号ID
     */
    private String agentId;

    /**
     * 最近登录的客户端版本号
     */
    private String versionCode;

    /**
     * 支付宝Uid
     */
    private String alipayUid;

    private String secret;

    private Boolean secretEnabled;


    @Override
    public String toString() {
        return "AccountCashierEntity{" +
                "id='" + id + '\'' +
                ", commissionRate=" + commissionRate +
                ", orderLimit=" + orderLimit +
                ", dailyOrderLimitAlipay=" + dailyOrderLimitAlipay +
                ", dailyOrderLimitWechat=" + dailyOrderLimitWechat +
                ", agentId='" + agentId + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", alipayUid='" + alipayUid + '\'' +
                ", secret='" + secret + '\'' +
                ", secretEnabled='" + secretEnabled + '\'' +
                '}';
    }
}
