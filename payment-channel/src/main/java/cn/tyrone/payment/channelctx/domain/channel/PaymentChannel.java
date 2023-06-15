package cn.tyrone.payment.channelctx.domain.channel;

import java.util.Map;

/**
 * 支付通道
 */
public class PaymentChannel {

    private Long id;

    /**
     * 支付通道唯一编码
     */
    private PaymentChannelUniqueCode paymentChannelUniqueCode;

    private String name;

    private Map<String, Object> paymentChannelConfig;

}
