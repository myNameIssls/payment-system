package cn.tyrone.payment.channel.domain.route.strategy.context;


import cn.tyrone.payment.channel.common.entity.AccountDetailsRequest;
import cn.tyrone.payment.channel.common.enums.ChannelConfigCode;
import cn.tyrone.payment.channel.common.enums.PaymentGatewayType;
import cn.tyrone.payment.channel.domain.route.strategy.AccountDetailsQueryRouteStrategy;
import cn.tyrone.payment.channel.domain.service.IPaymentRouteStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 账户明细查询路由策略上下文
 */
@Component
public class AccountDetailsQueryRouteStrategyContext {

    @Autowired
    private IPaymentRouteStrategyService paymentRouteStrategyService;

    @Autowired
    private Map<String, AccountDetailsQueryRouteStrategy> accountDetailsQueryRouteStrategyMap = new ConcurrentHashMap<>();

    public AccountDetailsQueryRouteStrategyContext(Map<String, AccountDetailsQueryRouteStrategy> accountDetailsQueryRouteStrategyMap) {
        this.accountDetailsQueryRouteStrategyMap.clear();
        accountDetailsQueryRouteStrategyMap.forEach(this.accountDetailsQueryRouteStrategyMap::put);
    }

    public AccountDetailsQueryRouteStrategy getAccountDetailsQueryRouteStrategy(AccountDetailsRequest request){
        PaymentGatewayType paymentGatewayType = PaymentGatewayType.ACCOUNT_DETAILS_QUERY;
        ChannelConfigCode channelConfigCode = request.getChannelConfigCode();

//        String strategyName = PaymentRouteStrategyConfig.getRouteStrategyServiceName(paymentGatewayType, channelConfigCode);
        String strategyName = paymentRouteStrategyService.paymentRouteStrategy(paymentGatewayType, channelConfigCode);

        AccountDetailsQueryRouteStrategy accountDetailsQueryRouteStrategy = this.accountDetailsQueryRouteStrategyMap.get(strategyName);

        return accountDetailsQueryRouteStrategy;
    }
}
