package com.zero.pay.Service.Impl;

import com.zero.pay.Currency.LogicException;
import com.zero.pay.Entity.ShopBalanceLogEntity;
import com.zero.pay.Dao.ShopBalanceLogMapper;
import com.zero.pay.Service.ShopBalanceLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 商家余额记录 服务实现类
 * </p>
 *
 * @author 麒麟
 * @since 2019-12-05
 */
@Service
public class ShopBalanceLogServiceImpl extends ServiceImpl<ShopBalanceLogMapper, ShopBalanceLogEntity> implements ShopBalanceLogService {


    @Override
    public boolean saveSshopLogData(ShopBalanceLogEntity shopBalanceLogEntity) {
        if (shopBalanceLogEntity.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw LogicException.le("商家增加的余额无效");
        }
        return this.save(shopBalanceLogEntity);
    }
}
