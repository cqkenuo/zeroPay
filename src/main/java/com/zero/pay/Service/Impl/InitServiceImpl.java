package com.zero.pay.Service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zero.pay.Entity.AccountCashierEntity;
import com.zero.pay.Entity.PayOrderEntity;
import com.zero.pay.Form.InitCashierzichanForm;
import com.zero.pay.Service.AccountCashierService;
import com.zero.pay.Service.AccountService;
import com.zero.pay.Service.InitService;
import com.zero.pay.Service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InitServiceImpl implements InitService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountCashierService cashierService;
    @Autowired
    private PayOrderService payOrderService;

    @Override
    public InitCashierzichanForm getZiChanData(String cashierId) {
        InitCashierzichanForm initCashierzichanForm = new InitCashierzichanForm();
        AccountCashierEntity byone = cashierService.getOne(Wrappers.<AccountCashierEntity>lambdaQuery().eq(AccountCashierEntity::getId, cashierId));
        if (byone != null) {
            initCashierzichanForm.setOrderRate(byone.getCommissionRate());
            initCashierzichanForm.setOrderLimit(byone.getOrderLimit());
            List<PayOrderEntity> payOrderEntityList = payOrderService.list(Wrappers.<PayOrderEntity>lambdaQuery().like(PayOrderEntity::getCashierId, byone.getId()).eq(PayOrderEntity::getStatus, "SUCCESS"));
            BigDecimal orderRate = new BigDecimal(0.00);
            for (PayOrderEntity entity : payOrderEntityList
            ) {
                orderRate = orderRate.add(entity.getAmount());
            }
            initCashierzichanForm.setOrderRate(orderRate);
        return initCashierzichanForm;
        }
        return initCashierzichanForm;
    }
}
