package com.zero.pay.Entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 商家余额记录
 * </p>
 *
 * @author 麒麟
 * @since 2019-12-05
 */
@TableName("shop_balance_log")
@Data
public class ShopBalanceLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 商家ID，关联account_shop表
     */
    private String shopId;

    /**
     * 金额，单位：元
     */
    private BigDecimal amount;

    /**
     * 变更后余额，单位：元
     */
    private BigDecimal balanceAfter;

    /**
     * 类型。WITHDRAWAL：发布提现；WITHDRAWAL_CHARGE：提现手续费；ORDER_PAID：收款；LIQUIDATION：退出清算；MANUAL_APPLY：手动收款
     */
    private String category;

    /**
     * 商家名称快照
     */
    private String shopName;

    /**
     * 交易ID
     */
    private String transactionId;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间。本地时间，格式：yyyy-MM-dd HH:mm:ss。例如：2099-01-02 04:05:06
     */
    private String createTime;


    @Override
    public String toString() {
        return "ShopBalanceLogEntity{" +
        "id=" + id +
        ", shopId=" + shopId +
        ", amount=" + amount +
        ", balanceAfter=" + balanceAfter +
        ", category=" + category +
        ", shopName=" + shopName +
        ", transactionId=" + transactionId +
        ", memo=" + memo +
        ", createTime=" + createTime +
        "}";
    }
}
