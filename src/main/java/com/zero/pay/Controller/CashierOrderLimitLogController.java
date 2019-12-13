package com.zero.pay.Controller;

import com.zero.pay.Currency.JsonUitls;
import com.zero.pay.Currency.Result;
import com.zero.pay.Entity.CashierOrderLimitLogEntity;
import com.zero.pay.Service.CashierOrderLimitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 收款人额度记录 前端控制器
 * </p>
 *
 * @author 麒麟
 * @since 2019-11-30
 */
@Controller
@RequestMapping("/cashierOrderLimitLog")
public class CashierOrderLimitLogController {

    @Autowired
    private CashierOrderLimitLogService orderLimitLogService;

//    @PostMapping("/finCashierAll")
//    @ResponseBody
//    public Result finCashierAll(@Valid String cashierId) {
//        List<CashierOrderLimitLogEntity> limitLogEntities = orderLimitLogService.finCashierAll(cashierId);
//        List<CashierOrderLimitLogEntity> entities = new ArrayList<>();
//        for (CashierOrderLimitLogEntity entity : limitLogEntities
//        ) {
//            switch (entity.getCategory()) {
//                case ORDER_PAID:
//                    entity.setMsg("订单支付成功");
//                    break;
//                case INCREASE_ORDER_LIMIT:
//                    entity.setMsg("代理增加收款人额度");
//                    break;
//                case APPLY_APPROVED:
//                    entity.setMsg("提现申请单审核成功");
//                    break;
//                case MANUAL_APPLY:
//                    entity.setMsg("平台手动收款");
//                    break;
//            }
//            entities.add(entity);
//        }
//        return Result.success("success", JsonUitls.objectToJson(limitLogEntities));
//    }


}

