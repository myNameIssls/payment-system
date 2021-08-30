package cn.tyrone.payment.channel.infrastructure.api.service.impl;

import cn.tyrone.payment.channel.common.enums.ChannelConfigCode;
import cn.tyrone.payment.channel.common.enums.PaymentGatewayType;
import cn.tyrone.payment.channel.domain.service.IPaymentRouteStrategyService;
import cn.tyrone.payment.channel.infrastructure.api.common.enums.PaymentRouteStrategyConfig;
import org.springframework.stereotype.Service;

@Service
public class PaymentRouteStrategyServiceImpl implements IPaymentRouteStrategyService {

    /**
     * 根据支付网关类型和渠道配置编码获取支付路由策略
     * @param paymentGatewayType
     * @param channelConfigCode
     * @return
     */
    @Override
    public String paymentRouteStrategy(PaymentGatewayType paymentGatewayType, ChannelConfigCode channelConfigCode) {

        String strategyName = PaymentRouteStrategyConfig.getRouteStrategyServiceName(paymentGatewayType, channelConfigCode);

        return strategyName;
    }
}
