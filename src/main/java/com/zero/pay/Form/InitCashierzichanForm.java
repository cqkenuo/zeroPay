package com.zero.pay.Form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InitCashierzichanForm {
    /**
     * 所剩金额
     */
    private BigDecimal orderLimit;
    /**
     * 收益
     */
    private BigDecimal orderProfit;
    /**
     * 已经交易的金额
     */
    private BigDecimal orderRate;
    /**
     * 目前再申请充值的金额
     */
    private BigDecimal receivables;

    public InitCashierzichanForm() {
        this.orderLimit = new BigDecimal(0.00);
        this.orderProfit = new BigDecimal(0.00);
        this.orderRate = new BigDecimal(0.00);
        this.receivables = new BigDecimal(0.00);
    }

    @Override
    public String toString() {
        return "InitCashierzichanForm{" +
                "orderLimit=" + orderLimit +
                ", orderProfit=" + orderProfit +
                ", orderRate=" + orderRate +
                ", receivables=" + receivables +
                '}';
    }
}
