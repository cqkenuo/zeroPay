package com.zero.pay.Entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 代理余额记录
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@TableName("agent_balance_log")
@Data
public class AgentBalanceLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 代理ID，关联account_agent表
     */
    private String agentId;

    /**
     * 金额，单位：元
     */
    private BigDecimal amount;

    /**
     * 变更后余额，单位：元
     */
    private BigDecimal balanceAfter;

    /**
     * 类型。COMMISSION：手续费；INCREASE_ORDER_LIMIT：增加收款额度；
PLATFORM_INCREASE：平台增加。LIQUIDATION：商家退出清算；SHOP_SETTLE：平台对商家结账
     */
    private String category;

    /**
     * 商家ID，关联account_shop表
     */
    private String shopId;

    /**
     * 商家名称快照
     */
    private String shopName;

    /**
     * 收款人ID，关联account_cashier表
     */
    private String cashierId;

    /**
     * 收款人名称快照
     */
    private String cashierName;

    /**
     * 交易ID
     */
    private String transactionId;

    /**
     * 创建时间。本地时间，格式：yyyy-MM-dd HH:mm:ss。例如：2099-01-02 04:05:06
     */
    private String createTime;


    @Override
    public String toString() {
        return "AgentBalanceLogEntity{" +
        "id=" + id +
        ", agentId=" + agentId +
        ", amount=" + amount +
        ", balanceAfter=" + balanceAfter +
        ", category=" + category +
        ", shopId=" + shopId +
        ", shopName=" + shopName +
        ", cashierId=" + cashierId +
        ", cashierName=" + cashierName +
        ", transactionId=" + transactionId +
        ", createTime=" + createTime +
        "}";
    }
}
