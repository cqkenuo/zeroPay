package com.zero.pay.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zero.pay.Entity.AccountEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账号 服务类
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-28
 */
public interface AccountService extends IService<AccountEntity> {

    boolean saveAccount(AccountEntity accountEntity);

    boolean updateAccount(AccountEntity accountEntity);
}
