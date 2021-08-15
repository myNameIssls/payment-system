package cn.tyrone.payment.channel.domain.route.strategy;


import cn.tyrone.payment.channel.common.entity.PaymentAccountRequest;
import cn.tyrone.payment.channel.common.entity.PaymentAccountResponse;

/**
 * 余额查询路由策略
 */
public interface BalanceQueryRouteStrategy {

    public PaymentAccountResponse balanceQuery(PaymentAccountRequest request);

}
