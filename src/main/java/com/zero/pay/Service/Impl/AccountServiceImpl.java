package com.zero.pay.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zero.pay.Entity.AccountEntity;
import com.zero.pay.Dao.AccountMapper;
import com.zero.pay.Service.AccountCashierService;
import com.zero.pay.Service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号 服务实现类
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-28
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountEntity> implements AccountService {

    @Override
    public boolean saveAccount(AccountEntity accountEntity) {
        return save(accountEntity);
    }

    @Override
    public boolean updateAccount(AccountEntity accountEntity) {

        return updateById(accountEntity);
    }

}
