package com.zero.pay.Service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zero.pay.Currency.Enum.PayTypeEnum;
import com.zero.pay.Currency.MD5Utils;
import com.zero.pay.Entity.CashierQrcodeEntity;
import com.zero.pay.Dao.CashierQrcodeMapper;
import com.zero.pay.Service.CashierQrcodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@Service
public class CashierQrcodeServiceImpl extends ServiceImpl<CashierQrcodeMapper, CashierQrcodeEntity> implements CashierQrcodeService {

    private Logger logger = LoggerFactory.getLogger(CashierQrcodeServiceImpl.class);


    @Override
    public boolean saveQrcode(CashierQrcodeEntity cashierQrcodeEntity) {

        return save(cashierQrcodeEntity);
    }

    @Override
    public boolean updateQrcodeStatus(String CashierId, String qrId) {
        CashierQrcodeEntity one = getOne(Wrappers.<CashierQrcodeEntity>lambdaQuery().eq(CashierQrcodeEntity::getCashierId, CashierId).eq(CashierQrcodeEntity::getId, qrId), false);
        if (one != null) {
//            boolean update = update(Wrappers.<CashierQrcodeEntity>lambdaUpdate().eq(CashierQrcodeEntity::getType, one.getType()).eq(CashierQrcodeEntity::getCashierId, one.getCashierId()).set(CashierQrcodeEntity::getEnabled, false));


            //将已经启用的取消
            CashierQrcodeEntity one1 = getOne(Wrappers.<CashierQrcodeEntity>lambdaQuery().eq(CashierQrcodeEntity::getCashierId, CashierId).eq(CashierQrcodeEntity::getEnabled, true).eq(CashierQrcodeEntity::getType, one.getType()), false);
            if (one1 != null) {
                one1.setEnabled(false);
                boolean b = updateById(one1);
                if (b) {
                    logger.info("替换启用二维码\t{} 替换为\t{}", one1.getId(), one.getId());
                }
            }
            one.setEnabled(true);
            boolean b = updateById(one);
            return b;
        }
        return false;
    }
}
