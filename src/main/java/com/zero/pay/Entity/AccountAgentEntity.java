package com.zero.pay.Entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 代理基本信息
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@TableName("account_agent")
@Data
public class AccountAgentEntity implements Serializable {

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
     * 收款费率，例如：0.002
     */
    private BigDecimal commissionRate;

    /**
     * 下属收款人最大数量，含
     */
    private Integer cashierLimit;


    @Override
    public String toString() {
        return "AccountAgentEntity{" +
        "id=" + id +
        ", balance=" + balance +
        ", commissionRate=" + commissionRate +
        ", cashierLimit=" + cashierLimit +
        "}";
    }
}
