package cn.tyrone.payment.channel.domain.channel;


import java.util.Map;

/**
 * 支付通道
 */
public class PaymentChannel {

    private String id;

    private String name;

    /**
     * 支付通道唯一编码
     */
    private PaymentChannelUniqueCode paymentChannelUniqueCode;

    private Map<String, Object> paymentChannelConfig;

}
