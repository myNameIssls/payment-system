package cn.tyrone.payment.channel.infrastructure.api.citic.strategy.impl;


import cn.tyrone.payment.channel.common.entity.PaymentAccountRequest;
import cn.tyrone.payment.channel.common.entity.PaymentAccountResponse;
import cn.tyrone.payment.channel.domain.adapter.ICiticPaymentServiceAdapterCMS;
import cn.tyrone.payment.channel.domain.route.strategy.BalanceQueryRouteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceQueryRouteStrategyCiticCMS implements BalanceQueryRouteStrategy {

    @Autowired
    private ICiticPaymentServiceAdapterCMS paymentServiceAdapterCMS;

    @Override
    public PaymentAccountResponse balanceQuery(PaymentAccountRequest request) {

        PaymentAccountResponse paymentAccountResponse = paymentServiceAdapterCMS.balanceQuery(request);

        return paymentAccountResponse;
    }
}
