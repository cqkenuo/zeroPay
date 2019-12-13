package com.zero.pay.Entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 收款人申请单
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@TableName("cashier_apply")
@Data
public class CashierApplyEntity implements Serializable {

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
     * 商家提现申请单ID，关联shop_withdrawal表
     */
    private String shopWithdrawalId;

    /**
     * 商家提现单唯一编号
     */
    private String shopWithdrawalUniqueNo;

    /**
     * 收款人ID，关联account_cashier表
     */
    private String cashierId;

    /**
     * 收款人名称快照
     */
    private String cashierName;

    /**
     * 收款人登录名快照
     */
    private String cashierUsername;

    /**
     * 申请金额，单位：元
     */
    private BigDecimal amount;

    /**
     * 收款人收款费率，例如：0.002
     */
    private BigDecimal commissionRate;

    /**
     * 代理ID，关联account_agent表
     */
    private String agentId;

    /**
     * 代理收款费率，例如：0.002
     */
    private BigDecimal agentCommissionRate;

    /**
     * 申请状态。CREATED：已申请；WAITING：待审核；DENIED：未通过；APPROVED：已通过；TIMEOUT：超时；CANCELED：已取消
     */
    private String status;

    /**
     * 付款凭证图片
     */
    private String image;

    /**
     * 审核时间。本地时间，格式：yyyy-MM-dd HH:mm:ss。例如：2099-01-02 04:05:06
     */
    private String approveTime;

    /**
     * 申请时间。本地时间，格式：yyyy-MM-dd HH:mm:ss。例如：2099-01-02 04:05:06
     */
    private String createTime;



    @Override
    public String toString() {
        return "CashierApplyEntity{" +
        "id=" + id +
        ", uniqueNo=" + uniqueNo +
        ", shopWithdrawalId=" + shopWithdrawalId +
        ", shopWithdrawalUniqueNo=" + shopWithdrawalUniqueNo +
        ", cashierId=" + cashierId +
        ", cashierName=" + cashierName +
        ", cashierUsername=" + cashierUsername +
        ", amount=" + amount +
        ", commissionRate=" + commissionRate +
        ", agentId=" + agentId +
        ", agentCommissionRate=" + agentCommissionRate +
        ", status=" + status +
        ", image=" + image +
        ", approveTime=" + approveTime +
        ", createTime=" + createTime +
        "}";
    }
}
