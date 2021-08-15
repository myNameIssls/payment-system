package cn.tyrone.payment.channel.domain.route.strategy.context;


import cn.tyrone.payment.channel.common.entity.PaymentAccountRequest;
import cn.tyrone.payment.channel.common.enums.ChannelConfigCode;
import cn.tyrone.payment.channel.common.enums.PaymentGatewayType;
import cn.tyrone.payment.channel.domain.route.strategy.BalanceQueryRouteStrategy;
import cn.tyrone.payment.channel.domain.service.IPaymentRouteStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BalanceQueryRouteStrategyContext {

    @Autowired
    private IPaymentRouteStrategyService paymentRouteStrategyService;

    @Autowired
    private Map<String, BalanceQueryRouteStrategy> balanceQueryRouteStrategyMap = new ConcurrentHashMap<>();

    public BalanceQueryRouteStrategyContext(Map<String, BalanceQueryRouteStrategy> balanceQueryRouteStrategyMap) {
        this.balanceQueryRouteStrategyMap.clear();
        balanceQueryRouteStrategyMap.forEach(this.balanceQueryRouteStrategyMap::put);
    }

    public BalanceQueryRouteStrategy getBalanceQueryRouteStrategy(PaymentAccountRequest request){
        PaymentGatewayType paymentGatewayType = PaymentGatewayType.BALANCE_QUERY;
        ChannelConfigCode channelConfigCode = request.getChannelConfigCode();

        String strategyName = paymentRouteStrategyService.paymentRouteStrategy(paymentGatewayType, channelConfigCode);

        BalanceQueryRouteStrategy balanceQueryRouteStrategy = this.balanceQueryRouteStrategyMap.get(strategyName);

        return balanceQueryRouteStrategy;
    }




}
