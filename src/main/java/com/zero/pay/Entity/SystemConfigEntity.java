package com.zero.pay.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 系统配置
 * </p>
 *
 * @author 麒麟
 * @since 2019-12-03
 */
@TableName("system_config")
@Data
public class SystemConfigEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 值
     */
    private String value;

    /**
     * 描述
     */
    private String description;


    @Override
    public String toString() {
        return "SystemConfigEntity{" +
        "id=" + id +
        ", value=" + value +
        ", description=" + description +
        "}";
    }
}
