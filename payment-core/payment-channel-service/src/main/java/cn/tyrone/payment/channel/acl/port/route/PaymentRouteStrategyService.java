package cn.tyrone.payment.channel.acl.port.route;


import cn.tyrone.payment.channel.domain.channel.PaymentChannelUniqueCode;
import cn.tyrone.payment.channel.domain.channel.PaymentGatewayType;

public interface PaymentRouteStrategyService {

    /**
     * 根据支付网关类型和渠道唯一编码获取支付路由策略名称
     * @param paymentGatewayType
     * @param paymentChannelUniqueCode
     * @return
     */
    public String paymentRouteStrategyServiceName(PaymentGatewayType paymentGatewayType, PaymentChannelUniqueCode paymentChannelUniqueCode);

}