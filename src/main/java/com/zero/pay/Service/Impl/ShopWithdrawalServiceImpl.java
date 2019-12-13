package com.zero.pay.Service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zero.pay.Entity.ShopWithdrawalEntity;
import com.zero.pay.Dao.ShopWithdrawalMapper;
import com.zero.pay.Service.ShopWithdrawalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商家提现单 服务实现类
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@Service
public class ShopWithdrawalServiceImpl extends ServiceImpl<ShopWithdrawalMapper, ShopWithdrawalEntity> implements ShopWithdrawalService {

    /**
     * 根据id查询提现单
     * @param id
     * @return
     */
    @Override
    public List<ShopWithdrawalEntity> qryShopWithDrawal(String id) {
        List<ShopWithdrawalEntity> shopWithdrawalEntityList = list(Wrappers.<ShopWithdrawalEntity>lambdaQuery().eq(ShopWithdrawalEntity::getId , id));
        return shopWithdrawalEntityList;
    }

    /**
     * 查询所有等待中的提现单
     */
    @Override
    public List<ShopWithdrawalEntity> listshopWithdrawalAvailable() {
        List<ShopWithdrawalEntity> shopWithdrawalEntityList = list(Wrappers.<ShopWithdrawalEntity>lambdaQuery().eq(ShopWithdrawalEntity ::getStatus,"CREATED"));
        return shopWithdrawalEntityList;
    }
}
