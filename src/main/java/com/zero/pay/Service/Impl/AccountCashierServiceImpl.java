package com.zero.pay.Service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zero.pay.Entity.AccountCashierEntity;
import com.zero.pay.Dao.AccountCashierMapper;
import com.zero.pay.Service.AccountCashierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收款人基本信息 服务实现类
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-28
 */
@Service
public class AccountCashierServiceImpl extends ServiceImpl<AccountCashierMapper, AccountCashierEntity> implements AccountCashierService {

    @Autowired
    private AccountCashierMapper accountCashierMapper;

    //查询密钥是否存在
    @Override
    public AccountCashierEntity qrySecret(String id) {
        AccountCashierEntity one = getOne(Wrappers.<AccountCashierEntity>lambdaQuery().eq(AccountCashierEntity::getId, id), false);
        return one;
    }

    //修改密钥
    @Override
    public boolean updSecret(String id , String secret) {
        AccountCashierEntity accountCashierEntity = getOne(Wrappers.<AccountCashierEntity>lambdaQuery().eq(AccountCashierEntity::getId, id) , false);

        accountCashierEntity.setSecret(secret);
        boolean b = updateById(accountCashierEntity);
        return b;
    }
}
