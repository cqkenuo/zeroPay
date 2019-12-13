package com.zero.pay.Dao;

import com.zero.pay.Entity.PayOrderNotifyLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 监控软件发来的订单通知 Mapper 接口
 * </p>
 *
 * @author 麒麟
 * @since 2019-12-03
 */
@Mapper
public interface PayOrderNotifyLogMapper extends BaseMapper<PayOrderNotifyLogEntity> {

}
