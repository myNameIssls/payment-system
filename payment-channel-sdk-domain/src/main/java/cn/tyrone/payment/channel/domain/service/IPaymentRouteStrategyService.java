package cn.tyrone.payment.channel.domain.service;

import cn.tyrone.payment.channel.common.enums.ChannelConfigCode;
import cn.tyrone.payment.channel.common.enums.PaymentGatewayType;

public interface IPaymentRouteStrategyService {

    /**
     * 根据支付网关类型和渠道配置编码获取支付路由策略
     * @param paymentGatewayType
     * @param channelConfigCode
     * @return
     */
    public String paymentRouteStrategy(PaymentGatewayType paymentGatewayType, ChannelConfigCode channelConfigCode);

}
