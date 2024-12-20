package cn.tyrone.payment.channel.acl.adapter.route;

import cn.tyrone.payment.channel.acl.port.route.PaymentRouteStrategyService;
import cn.tyrone.payment.channel.enums.ChannelCode;
import cn.tyrone.payment.channel.domain.channel.PaymentGatewayType;
import org.springframework.stereotype.Service;


@Service
public class PaymentRouteStrategyServiceImpl implements PaymentRouteStrategyService {

    /**
     * 根据支付网关类型和渠道唯一编码获取支付路由策略名称
     * @param paymentGatewayType
     * @param channelCode
     * @return
     */
    @Override
    public String paymentRouteStrategyServiceName(PaymentGatewayType paymentGatewayType, ChannelCode channelCode) {

        String paymentRouteStrategyServiceName = PaymentRouteStrategyConfig.getPaymentRouteStrategyServiceName(paymentGatewayType, channelCode);

        return paymentRouteStrategyServiceName;
    }
}
