package cn.tyrone.payment.channelctx.acl.adapter.route;

import cn.tyrone.payment.channelctx.acl.port.route.PaymentRouteStrategyService;
import cn.tyrone.payment.channelctx.domain.PaymentChannelUniqueCode;
import cn.tyrone.payment.channelctx.domain.PaymentGatewayType;
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
