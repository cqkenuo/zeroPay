package com.zero.pay.Service;

import com.zero.pay.Entity.CashierOrderLimitLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 收款人额度记录 服务类
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
public interface CashierOrderLimitLogService extends IService<CashierOrderLimitLogEntity> {
    List<CashierOrderLimitLogEntity> finCashierAll(String cashierId);

    Boolean saveCashierLogData(CashierOrderLimitLogEntity cashierOrderLimitLogEntity);
}
