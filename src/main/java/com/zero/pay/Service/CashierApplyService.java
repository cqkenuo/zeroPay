package com.zero.pay.Service;

import com.zero.pay.Entity.CashierApplyEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 收款人申请单 服务类
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
public interface CashierApplyService extends IService<CashierApplyEntity> {


    boolean saveRemittance(String shopWithdrawalId, String cashierId, BigDecimal amount);

    boolean addCashierApply(CashierApplyEntity applyEntity);

}
