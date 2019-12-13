package com.zero.pay.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.zero.pay.Currency.Enum.CategoryEnum;
import lombok.Data;
import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.decimal.DecimalMinValidatorForBigInteger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * <p>
 * 账号
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-28
 */
@TableName("account")
@Data
public class AccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 账号类型。PLATFORM：平台；SHOP：商家；CASHIER：收款人；AGENT：代理；SHOP_AGENT：商家代理
     */
    private String category;

    /**
     * 名称
     */
    private String name;

    /**
     * 登录名，即手机号码
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 是否允许登录
     */
    private Boolean enabled;

    /**
     * 登录密码的md5
     */
    private String passwordHash;

    /**
     * 监控软件传来的设备序列号
     */
    private String serialNo;

    /**
     * 创建时间。本地时间，格式：yyyy-MM-dd HH:mm:ss。例如：2099-01-02 04:05:06
     */
    private String createTime;


    @Override
    public String toString() {
        return "AccountEntity{" +
        "id=" + id +
        ", category=" + category +
        ", name=" + name +
        ", username=" + username +
        ", password=" + password +
        ", enabled=" + enabled +
        ", passwordHash=" + passwordHash +
        ", serialNo=" + serialNo +
        ", createTime=" + createTime +
        "}";
    }

    public AccountEntity() {
        this.setCategory(CategoryEnum.CASHIER.toString());
    }

}
