package cn.tyrone.payment.channel.domain.route.strategy.context;


import cn.tyrone.payment.channel.common.entity.TransferAccountRequest;
import cn.tyrone.payment.channel.common.enums.ChannelConfigCode;
import cn.tyrone.payment.channel.common.enums.PaymentGatewayType;
import cn.tyrone.payment.channel.domain.route.strategy.TransferAccountRouteStrategy;
import cn.tyrone.payment.channel.domain.service.IPaymentRouteStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TransferAccountRouteStrategyContext {

    @Autowired
    private IPaymentRouteStrategyService paymentRouteStrategyService;

    @Autowired
    private Map<String, TransferAccountRouteStrategy> transferAccountRouteStrategyMap = new ConcurrentHashMap<>();

    public TransferAccountRouteStrategyContext(Map<String, TransferAccountRouteStrategy> transferAccountRouteStrategyMap) {
        this.transferAccountRouteStrategyMap.clear();
        transferAccountRouteStrategyMap.forEach(this.transferAccountRouteStrategyMap::put);
    }

    public TransferAccountRouteStrategy geTransferAccountRouteStrategy(TransferAccountRequest request){
        PaymentGatewayType paymentGatewayType = PaymentGatewayType.TRANSFER_ACCOUNT;
        ChannelConfigCode channelConfigCode = request.getChannelConfigCode();

        String strategyName = paymentRouteStrategyService.paymentRouteStrategy(paymentGatewayType, channelConfigCode);

        TransferAccountRouteStrategy transferAccountRouteStrategy = this.transferAccountRouteStrategyMap.get(strategyName);

        return transferAccountRouteStrategy;
    }




}
