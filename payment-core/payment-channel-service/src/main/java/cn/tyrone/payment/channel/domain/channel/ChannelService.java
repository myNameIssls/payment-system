package cn.tyrone.payment.channel.domain.channel;

import cn.tyrone.payment.channel.pl.AddChannelCommand;
import cn.tyrone.payment.sdk.common.pl.Result;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {
    public Result<Void> addChannel(AddChannelCommand command) {

        Channel channel = new Channel(command);

        return Result.success();
    }
}
