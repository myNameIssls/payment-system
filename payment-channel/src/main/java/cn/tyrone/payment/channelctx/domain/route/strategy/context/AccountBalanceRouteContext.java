package cn.tyrone.payment.channelctx.domain.route.strategy.context;

import cn.tyrone.payment.channelctx.acl.port.route.PaymentRouteStrategyService;
import cn.tyrone.payment.channelctx.domain.channel.PaymentChannelUniqueCode;
import cn.tyrone.payment.channelctx.domain.channel.PaymentGatewayType;
import cn.tyrone.payment.channelctx.domain.route.strategy.AccountBalanceRoute;
import cn.tyrone.payment.channelctx.pl.AccountBalanceRequest;
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

        PaymentChannelUniqueCode paymentChannelUniqueCode = PaymentChannelUniqueCode.of(uniqueValue);

        String paymentRouteStrategyServiceName = paymentRouteStrategyService.paymentRouteStrategyServiceName(PaymentGatewayType.ACCOUNT_BALANCE, paymentChannelUniqueCode);
        AccountBalanceRoute accountBalanceRoute = this.accountBalanceRouteMap.get(paymentRouteStrategyServiceName);

        return accountBalanceRoute;
    }

}
