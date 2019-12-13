package com.zero.pay.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.pay.Entity.ShopWithdrawalEntity;

import java.util.List;

/**
 * <p>
 * 商家提现单 服务类
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
public interface ShopWithdrawalService extends IService<ShopWithdrawalEntity> {

    /**
     * 根据id查询提现单
     * @param id
     * @return
     */
    List<ShopWithdrawalEntity> qryShopWithDrawal(String id);

    List<ShopWithdrawalEntity> listshopWithdrawalAvailable();

}
