package com.zero.pay.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.pay.Entity.CashierQrcodeEntity;
import com.zero.pay.Entity.ShopWithdrawalEntity;

/**
 * Create  by wgh on 2019/12/5 3:06.
 */
public interface SqueakManageService extends IService<CashierQrcodeEntity> {

    //新增支付宝吱口令
    boolean saveSqueak(CashierQrcodeEntity cashierQrcodeEntity);

    //查看详情信息
    CashierQrcodeEntity qryInfo(String id);
}
