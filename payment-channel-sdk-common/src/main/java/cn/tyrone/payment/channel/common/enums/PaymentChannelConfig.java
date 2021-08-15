package cn.tyrone.payment.channel.common.enums;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 支付通道配置
 */
@Getter
@Setter
@Builder
public class PaymentChannelConfig {

    /**
     * 支付渠道
     */
    private PaymentChannelType paymentChannelType;

    /**
     * 支付渠道配置编码
     */
    private ChannelConfigCode channelConfigCode;

    /**
     * 支付渠道配置
     */
    private Map<String, Object> channelConfig;

}
