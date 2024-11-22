package cn.tyrone.payment.channel.domain.channel;


import java.util.Map;

/**
 * 支付通道
 */
public class Channel {

    private String channelId;

    private String name;

    /**
     * 支付通道唯一编码
     */
    private ChannelCode channelCode;

    private Map<String, Object> channelConfig;

}
