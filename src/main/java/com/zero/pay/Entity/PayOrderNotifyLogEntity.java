package com.zero.pay.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 监控软件发来的订单通知
 * </p>
 *
 * @author 麒麟
 * @since 2019-12-03
 */
@TableName("pay_order_notify_log")
@Data
public class PayOrderNotifyLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建日期。本地时间，格式：yyyy-MM-dd。例如：2099-01-02
     */
    private String createTime;


    @Override
    public String toString() {
        return "PayOrderNotifyLogEntity{" +
        "id=" + id +
        ", content=" + content +
        ", createTime=" + createTime +
        "}";
    }
}
