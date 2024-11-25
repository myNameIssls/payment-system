package cn.tyrone.payment.channel.pl;

import cn.tyrone.payment.channel.enums.ChannelCode;
import cn.tyrone.payment.sdk.common.pl.Command;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class AddChannelCommand implements Command {

    private String name;

    /**
     * 支付通道唯一编码
     */
    private ChannelCode channelCode;

    private Map<String, Object> channelConfig;

}
