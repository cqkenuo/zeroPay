package com.zero.pay.Service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.pay.Dao.CashierQrcodeMapper;
import com.zero.pay.Entity.CashierQrcodeEntity;
import com.zero.pay.Service.SqueakManageService;
import org.springframework.stereotype.Service;

/**
 * Create  by wgh on 2019/12/5 3:07.
 */
@Service
public class SqueakManageServiceImpl extends ServiceImpl<CashierQrcodeMapper, CashierQrcodeEntity> implements SqueakManageService {

    //新增支付宝吱口令
    @Override
    public boolean saveSqueak(CashierQrcodeEntity cashierQrcodeEntity) {
        return save(cashierQrcodeEntity);
    }

    @Override
    public CashierQrcodeEntity qryInfo(String id ) {
        CashierQrcodeEntity cashierQrcodeEntity = getOne(Wrappers.<CashierQrcodeEntity>lambdaQuery().eq(CashierQrcodeEntity::getId , id) , false);
        return cashierQrcodeEntity;
    }
}
