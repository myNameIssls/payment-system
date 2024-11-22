package cn.tyrone.payment.channel.domain.route.strategy.context;

import cn.tyrone.payment.channel.acl.port.route.PaymentRouteStrategyService;
import cn.tyrone.payment.channel.domain.channel.ChannelCode;
import cn.tyrone.payment.channel.domain.channel.PaymentGatewayType;
import cn.tyrone.payment.channel.domain.route.strategy.AccountBalanceRoute;
import cn.tyrone.payment.channel.pl.AccountBalanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 开户路由策略上下文
 */
@Component
public class AccountBalanceRouteContext {

    @Autowired
    private PaymentRouteStrategyService paymentRouteStrategyService;

    @Autowired
    private Map<String, AccountBalanceRoute> accountBalanceRouteMap = new ConcurrentHashMap<>();

    public AccountBalanceRouteContext(Map<String, AccountBalanceRoute> accountBalanceRouteMap) {
        this.accountBalanceRouteMap.clear();
        accountBalanceRouteMap.forEach(this.accountBalanceRouteMap::put);
    }

    /**
     * 获取开户路由
     * @param accountBalanceRequest
     * @return
     */
    public AccountBalanceRoute getOpenAccountRoute(AccountBalanceRequest accountBalanceRequest){

        String uniqueValue = accountBalanceRequest.getPaymentChannelUniqueCode();

        ChannelCode channelCode = ChannelCode.of(uniqueValue);

        String paymentRouteStrategyServiceName = paymentRouteStrategyService.paymentRouteStrategyServiceName(PaymentGatewayType.ACCOUNT_BALANCE, channelCode);
        AccountBalanceRoute accountBalanceRoute = this.accountBalanceRouteMap.get(paymentRouteStrategyServiceName);

        return accountBalanceRoute;
    }

}
