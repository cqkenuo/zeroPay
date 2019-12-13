package com.zero.pay.Entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zero.pay.Currency.Enum.CashierOrderLimitLogCategoryEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 收款人额度记录
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@TableName("cashier_order_limit_log")
@Data
public class CashierOrderLimitLogEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 收款人ID，关联account_cashier表
     */
    private String cashierId;

    /**
     * 收款人名称
     */
    private String cashierName;

    /**
     * 收款人登录名，即手机号码
     */
    private String cashierUsername;

    /**
     * 金额，单位：元
     */
    private BigDecimal amount;

    /**
     * 变更后的额度，单位：元
     */
    private BigDecimal orderLimitAfter;

    /**
     * 类型。ORDER_PAID：订单支付成功；INCREASE_ORDER_LIMIT：代理增加收款人额度；APPLY_APPROVED：提现申请单审核成功；MANUAL_APPLY：平台手动收款
     */
    private String category;

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
        return "CashierOrderLimitLogEntity{" +
        "id=" + id +
        ", cashierId=" + cashierId +
        ", cashierName=" + cashierName +
        ", cashierUsername=" + cashierUsername +
        ", amount=" + amount +
        ", orderLimitAfter=" + orderLimitAfter +
        ", category=" + category +
        ", memo=" + memo +
        ", createTime=" + createTime +
        "}";
    }
}
