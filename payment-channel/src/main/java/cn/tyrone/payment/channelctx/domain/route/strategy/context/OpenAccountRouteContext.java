package cn.tyrone.payment.channelctx.domain.route.strategy.context;

import cn.tyrone.payment.channelctx.acl.port.route.PaymentRouteStrategyService;
import cn.tyrone.payment.channelctx.domain.channel.PaymentChannelUniqueCode;
import cn.tyrone.payment.channelctx.domain.channel.PaymentGatewayType;
import cn.tyrone.payment.channelctx.domain.route.strategy.OpenAccountRoute;
import cn.tyrone.payment.channelctx.pl.OpenAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 开户路由策略上下文
 */
@Component
public class OpenAccountRouteContext {

    @Autowired
    private PaymentRouteStrategyService paymentRouteStrategyService;

    @Autowired
    private Map<String, OpenAccountRoute> openAccountRouteMap = new ConcurrentHashMap<>();

    public OpenAccountRouteContext(Map<String, OpenAccountRoute> openAccountRouteMap) {
        this.openAccountRouteMap.clear();
        openAccountRouteMap.forEach(this.openAccountRouteMap::put);
    }

    /**
     * 获取开户路由
     * @param openAccountRequest
     * @return
     */
    public OpenAccountRoute getOpenAccountRoute(OpenAccountRequest openAccountRequest){

        String uniqueValue = openAccountRequest.getPaymentChannelUniqueCode();

        PaymentChannelUniqueCode paymentChannelUniqueCode = PaymentChannelUniqueCode.of(uniqueValue);

        String paymentRouteStrategyServiceName = paymentRouteStrategyService.paymentRouteStrategyServiceName(PaymentGatewayType.OPEN_ACCOUNT, paymentChannelUniqueCode);
        OpenAccountRoute openAccountRoute = this.openAccountRouteMap.get(paymentRouteStrategyServiceName);

        return openAccountRoute;
    }

}
