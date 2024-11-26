package cn.tyrone.payment.channel.domain.channel;


import cn.tyrone.payment.channel.enums.ChannelCode;
import cn.tyrone.payment.channel.pl.AddChannelCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 支付通道
 */
@Getter
@Setter
public class Channel {

    private String channelId;

    private String name;

    /**
     * 支付通道唯一编码
     */
    private ChannelCode channelCode;

    private Map<String, Object> channelConfig;

    public Channel(AddChannelCommand command) {

        this.name = command.getName();
        this.channelCode = command.getChannelCode();
        this.channelConfig = command.getChannelConfig();

    }
}
