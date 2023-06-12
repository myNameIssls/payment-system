package cn.tyrone.payment.channelctx.acl.adapter.route;

import cn.tyrone.payment.channelctx.domain.channel.PaymentChannelUniqueCode;
import cn.tyrone.payment.channelctx.domain.channel.PaymentGatewayType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class PaymentRouteStrategyConfigTest {

    @Test
    void getPaymentRouteStrategyServiceName() {

        PaymentGatewayType paymentGatewayType = PaymentGatewayType.OPEN_ACCOUNT;
        PaymentChannelUniqueCode paymentChannelUniqueCode = PaymentChannelUniqueCode.CPCN_ONE;

        String routeStrategyServiceName = PaymentRouteStrategyConfig.getPaymentRouteStrategyServiceName(paymentGatewayType, paymentChannelUniqueCode);

        log.debug("路由策略服务名称：{}", routeStrategyServiceName);

    }
}