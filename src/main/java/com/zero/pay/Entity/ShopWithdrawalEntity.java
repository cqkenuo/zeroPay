package com.zero.pay.Entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 商家提现单
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@TableName("shop_withdrawal")
@Data
public class ShopWithdrawalEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 唯一编号
     */
    private String uniqueNo;

    /**
     * 商家账号ID，关联account_shop表
     */
    private String shopId;

    /**
     * 商家名称快照
     */
    private String shopName;

    /**
     * 商家登录名快照
     */
    private String shopUsername;

    /**
     * 提现金额，单位：元
     */
    private BigDecimal amount;

    /**
     * 提现手续费率，例如：0.002
     */
    private BigDecimal chargeRate;

    /**
     * 提现手续费，单位：元
     */
    private BigDecimal charge;

    /**
     * 提现状态。CREATED：提现中；FINISHED：已提现
     */
    private String status;

    /**
     * 提现前余额，单位：元
     */
    private BigDecimal balanceBefore;

    /**
     * 提现后余额，单位：元
     */
    private BigDecimal balanceAfter;

    /**
     * 开户名称
     */
    private String bankAccountName;

    /**
     * 银行账号
     */
    private String bankAccountNo;

    /**
     * 开户银行
     */
    private String bankName;

    /**
     * 申请提现时间。本地时间，格式：yyyy-MM-dd HH:mm:ss。例如：2099-01-02 04:05:06
     */
    private String createTime;

    /**
     * 完成提现时间。本地时间，格式：yyyy-MM-dd HH:mm:ss。例如：2099-01-02 04:05:06
     */
    private String finishTime;


    @Override
    public String toString() {
        return "ShopWithdrawalEntity{" +
        "id=" + id +
        ", uniqueNo=" + uniqueNo +
        ", shopId=" + shopId +
        ", shopName=" + shopName +
        ", shopUsername=" + shopUsername +
        ", amount=" + amount +
        ", chargeRate=" + chargeRate +
        ", charge=" + charge +
        ", status=" + status +
        ", balanceBefore=" + balanceBefore +
        ", balanceAfter=" + balanceAfter +
        ", bankAccountName=" + bankAccountName +
        ", bankAccountNo=" + bankAccountNo +
        ", bankName=" + bankName +
        ", createTime=" + createTime +
        ", finishTime=" + finishTime +
        "}";
    }
}
