package cn.tyrone.payment.channel.infrastructure.api.service.impl;

import cn.tyrone.payment.channel.common.enums.ChannelConfigCode;
import cn.tyrone.payment.channel.common.enums.PaymentGatewayType;
import cn.tyrone.payment.channel.domain.service.IPaymentRouteStrategyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {
                PaymentRouteStrategyServiceImpl.class,
        })
class PaymentRouteStrategyServiceImplTest {

    @Autowired
    private IPaymentRouteStrategyService paymentRouteStrategyService;

    /**
     * 根据支付网关类型和渠道配置编码获取支付路由策略-单元测试
     */
    @Test
    void paymentRouteStrategy() {

        PaymentGatewayType paymentGatewayType = PaymentGatewayType.ACCOUNT_DETAILS_QUERY;
        ChannelConfigCode channelConfigCode = ChannelConfigCode.CPCN_ONE;

        String strategyName = paymentRouteStrategyService.paymentRouteStrategy(paymentGatewayType, channelConfigCode);

        System.out.println("支付渠道策略：" + strategyName);

    }
}