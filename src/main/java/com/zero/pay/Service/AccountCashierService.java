package com.zero.pay.Service;

import com.zero.pay.Entity.AccountCashierEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 收款人基本信息 服务类
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-28
 */
public interface AccountCashierService extends IService<AccountCashierEntity> {


    //查询密钥
    AccountCashierEntity qrySecret(String id);

    //修改密钥
    boolean updSecret(String id , String secret);
}
