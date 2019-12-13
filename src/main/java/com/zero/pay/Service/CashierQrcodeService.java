package com.zero.pay.Service;

import com.zero.pay.Entity.CashierQrcodeEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
public interface CashierQrcodeService extends IService<CashierQrcodeEntity> {

    //新增二维码
    boolean saveQrcode(CashierQrcodeEntity cashierQrcodeEntity);

    //    替换启用的二维码
    boolean updateQrcodeStatus(String CashierId, String qrId);

}
