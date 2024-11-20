package cn.tyrone.payment.channel.domain.channel;

import cn.tyrone.payment.channel.enums.PaymentChannelCode;

import java.util.Map;

public class PaymentChannel {

    private String paymentChannelId;

    private String name;

    /**
     * 支付通道唯一编码
     */
    private PaymentChannelCode paymentChannelCode;

    private Map<String, Object> paymentChannelConfig;

}
