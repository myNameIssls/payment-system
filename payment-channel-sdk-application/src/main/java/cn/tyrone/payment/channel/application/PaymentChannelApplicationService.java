package cn.tyrone.payment.channel.application;

import cn.tyrone.payment.channel.common.entity.TransferAccountRequest;
import cn.tyrone.payment.channel.common.entity.TransferAccountResponse;
import cn.tyrone.payment.channel.domain.route.strategy.TransferAccountRouteStrategy;
import cn.tyrone.payment.channel.domain.route.strategy.context.TransferAccountRouteStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentChannelApplicationService {

    @Autowired
    private TransferAccountRouteStrategyContext transferAccountRouteStrategyContext;

    /**
     * 转账
     * @param request
     * @return
     */
    public TransferAccountResponse transferAccount(TransferAccountRequest request) {

        TransferAccountRouteStrategy transferAccountRouteStrategy = transferAccountRouteStrategyContext.geTransferAccountRouteStrategy(request);

        TransferAccountResponse transferAccountResponse = transferAccountRouteStrategy.transferAccount(request);

        return transferAccountResponse;
    }

}
