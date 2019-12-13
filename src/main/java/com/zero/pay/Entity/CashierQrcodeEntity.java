package com.zero.pay.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@TableName("cashier_qrcode")
@Data
@Getter
@Setter
public class CashierQrcodeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 唯一id  通过 MD5Utils.UUID（） 生成
     */
    private String id;
    /**
     * 收款人id
     */
    private String cashierId;
    /**
     * 'MERGEPAY'：聚合码,'UNIONPAY'：云闪付,'ALIPAY'：支付宝,'WECHAT'：微信, 'ALIPAYZKL'：支付宝吱口令
     */
    private String type;
    /**
     * 0:二维码启用状态  1:二维码禁止状态
     */
    private Boolean enabled;
    /**
     * 二维码解析出来的内容
     */
    private String imgUrl;
    /**
     * 图片存放的路径
     */
    private String imgQrcode;
    /**
     * 该二维码的主人名字
     */
    private String imgUsername;
    /**
     * 该二维码的联系人电话
     */
    private String imgPhone;
    /**
     * 创建时间
     */
    private LocalDateTime creatimeTime;

    @Override
    public String toString() {
        return "CashierQrcodeEntity{" +
        "id=" + id +
        ", cashierId=" + cashierId +
        ", type=" + type +
        ", enabled=" + enabled +
        ", imgUrl=" + imgUrl +
        ", imgQrcode=" + imgQrcode +
        ", imgUsername=" + imgUsername +
        ", imgPhone=" + imgPhone +
        ", creatimeTime=" + creatimeTime +
        "}";
    }
}
