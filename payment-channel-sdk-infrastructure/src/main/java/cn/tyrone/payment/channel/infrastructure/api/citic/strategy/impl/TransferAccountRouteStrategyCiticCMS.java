package cn.tyrone.payment.channel.infrastructure.api.citic.strategy.impl;


import cn.tyrone.payment.channel.common.entity.TransferAccountRequest;
import cn.tyrone.payment.channel.common.entity.TransferAccountResponse;
import cn.tyrone.payment.channel.domain.adapter.ICiticPaymentServiceAdapterCMS;
import cn.tyrone.payment.channel.domain.route.strategy.TransferAccountRouteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferAccountRouteStrategyCiticCMS implements TransferAccountRouteStrategy {

    @Autowired
    private ICiticPaymentServiceAdapterCMS paymentServiceAdapterCMS;

    @Override
    public TransferAccountResponse transferAccount(TransferAccountRequest request) {

        TransferAccountResponse transferAccountResponse = paymentServiceAdapterCMS.transferAccount(request);

        return transferAccountResponse;
    }

}
