package com.zero.pay.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zero.pay.Entity.CashierOrderLimitLogEntity;
import com.zero.pay.Dao.CashierOrderLimitLogMapper;
import com.zero.pay.Service.CashierOrderLimitLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 收款人额度记录 服务实现类
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@Service
public class CashierOrderLimitLogServiceImpl extends ServiceImpl<CashierOrderLimitLogMapper, CashierOrderLimitLogEntity> implements CashierOrderLimitLogService {

    @Override
    public List<CashierOrderLimitLogEntity> finCashierAll(String cashierId) {
        List<CashierOrderLimitLogEntity> list = list(Wrappers.<CashierOrderLimitLogEntity>lambdaQuery().like(CashierOrderLimitLogEntity::getCashierId, cashierId).orderByDesc(CashierOrderLimitLogEntity::getCreateTime));
        return list;
    }

    @Override
    public Boolean saveCashierLogData(CashierOrderLimitLogEntity cashierOrderLimitLogEntity) {

        return this.save(cashierOrderLimitLogEntity);
    }
}
