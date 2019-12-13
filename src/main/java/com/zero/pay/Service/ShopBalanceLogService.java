package com.zero.pay.Service;

import com.zero.pay.Entity.ShopBalanceLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商家余额记录 服务类
 * </p>
 *
 * @author 麒麟
 * @since 2019-12-05
 */
public interface ShopBalanceLogService extends IService<ShopBalanceLogEntity> {

    boolean saveSshopLogData(ShopBalanceLogEntity shopBalanceLogEntity);
}
