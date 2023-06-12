package cn.tyrone.payment.channelctx.acl.adapter.route;

import cn.tyrone.payment.channelctx.acl.port.route.PaymentRouteStrategyService;
import cn.tyrone.payment.channelctx.domain.channel.PaymentChannelUniqueCode;
import cn.tyrone.payment.channelctx.domain.channel.PaymentGatewayType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {
                PaymentRouteStrategyService.class, PaymentRouteStrategyServiceImpl.class
        })
class PaymentRouteStrategyServiceImplTest {

    @Autowired
    private PaymentRouteStrategyService paymentRouteStrategyService;

    @Test
    void paymentRouteStrategyServiceName() {

        PaymentGatewayType paymentGatewayType = PaymentGatewayType.OPEN_ACCOUNT;
        PaymentChannelUniqueCode paymentChannelUniqueCode = PaymentChannelUniqueCode.CPCN_ONE;

        String paymentRouteStrategyServiceName = paymentRouteStrategyService.paymentRouteStrategyServiceName(paymentGatewayType, paymentChannelUniqueCode);

        log.debug("路由策略服务名称：{}", paymentRouteStrategyServiceName);

    }
}