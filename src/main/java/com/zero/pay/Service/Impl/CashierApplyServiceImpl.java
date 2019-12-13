package com.zero.pay.Service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.pay.Currency.LogicException;
import com.zero.pay.Currency.MD5Utils;
import com.zero.pay.Dao.CashierApplyMapper;
import com.zero.pay.Entity.*;
import com.zero.pay.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 收款人申请单 服务实现类
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@Service
public class CashierApplyServiceImpl extends ServiceImpl<CashierApplyMapper, CashierApplyEntity> implements CashierApplyService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountCashierService accountCashierService;
    @Autowired
    private ShopWithdrawalService shopWithdrawalService;
    @Autowired
    private AccountAgentService accountAgentService;

    @Override
    public boolean saveRemittance(String shopWithdrawalId, String cashierId, BigDecimal amount) {
        ShopWithdrawalEntity withdrawalEntity = shopWithdrawalService.getOne(Wrappers.<ShopWithdrawalEntity>lambdaQuery().eq(ShopWithdrawalEntity::getId, shopWithdrawalId), false);
        if (withdrawalEntity != null && withdrawalEntity.getStatus().equals("CREATED")) {
            //查询汇款id是否存在
            CashierApplyEntity applyEntity = getOne(Wrappers.<CashierApplyEntity>lambdaQuery().eq(CashierApplyEntity::getShopWithdrawalId, withdrawalEntity.getId()).eq(CashierApplyEntity::getCashierId, cashierId), false);
            //查询用户id
            if (applyEntity != null) {
                if (applyEntity.getStatus().equals("CREATED") || applyEntity.getStatus().equals("WAITING")) {
                     throw LogicException.le("当前您已经有一个请求正在审核中");
                }
            }

            AccountEntity accountEntity = accountService.getById(cashierId);
            AccountCashierEntity cashierEntity = accountCashierService.getById(cashierId);
            AccountAgentEntity accountAgentEntity = accountAgentService.getById(cashierEntity.getAgentId());

            applyEntity.setId(MD5Utils.UUID());
            int count = count(Wrappers.<CashierApplyEntity>lambdaQuery().eq(CashierApplyEntity::getShopWithdrawalId, shopWithdrawalId));
            count = count + 1;
            applyEntity.setUniqueNo(withdrawalEntity.getUniqueNo() + count);
            applyEntity.setShopWithdrawalId(withdrawalEntity.getId());
            applyEntity.setShopWithdrawalUniqueNo(withdrawalEntity.getUniqueNo());
            applyEntity.setCashierId(cashierId);
            applyEntity.setCashierName(accountEntity.getName());
            applyEntity.setCashierUsername(accountEntity.getUsername());
            applyEntity.setAmount(amount);
            applyEntity.setCommissionRate(cashierEntity.getCommissionRate());
            applyEntity.setAgentId(cashierEntity.getAgentId());
            applyEntity.setAgentCommissionRate(accountAgentEntity.getCommissionRate());
            applyEntity.setStatus("CREATED");
            applyEntity.setImage("");
            applyEntity.setApproveTime("");
            applyEntity.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            boolean save = save(applyEntity);
            return save;
        }
        return false;
    }

    @Override
    public boolean addCashierApply(CashierApplyEntity applyEntity) {
        return save(applyEntity);
    }


}
