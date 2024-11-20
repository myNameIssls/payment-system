package cn.tyrone.payment.channel.acl.adapter.route;

import cn.tyrone.payment.channel.acl.port.route.PaymentRouteStrategyService;
import cn.tyrone.payment.channel.domain.channel.PaymentChannelUniqueCode;
import cn.tyrone.payment.channel.domain.channel.PaymentGatewayType;
import org.springframework.stereotype.Service;


@Service
public class PaymentRouteStrategyServiceImpl implements PaymentRouteStrategyService {

    /**
     * 根据支付网关类型和渠道唯一编码获取支付路由策略名称
     * @param paymentGatewayType
     * @param paymentChannelUniqueCode
     * @return
     */
    @Override
    public String paymentRouteStrategyServiceName(PaymentGatewayType paymentGatewayType, PaymentChannelUniqueCode paymentChannelUniqueCode) {

        String paymentRouteStrategyServiceName = PaymentRouteStrategyConfig.getPaymentRouteStrategyServiceName(paymentGatewayType, paymentChannelUniqueCode);

        return paymentRouteStrategyServiceName;
    }
}
