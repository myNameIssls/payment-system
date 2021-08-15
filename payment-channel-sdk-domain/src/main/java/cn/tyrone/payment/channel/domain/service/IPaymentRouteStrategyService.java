package cn.tyrone.payment.channel.domain.service;

import cn.tyrone.payment.channel.common.enums.ChannelConfigCode;
import cn.tyrone.payment.channel.common.enums.PaymentGatewayType;

public interface IPaymentRouteStrategyService {

    /**
     * 支付路由策略
     * @param paymentGatewayType
     * @return
     */
    public String paymentRouteStrategy(PaymentGatewayType paymentGatewayType, ChannelConfigCode channelConfigCode);

}
