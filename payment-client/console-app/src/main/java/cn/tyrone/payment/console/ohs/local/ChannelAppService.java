package cn.tyrone.payment.console.ohs.local;

import cn.tyrone.payment.channel.pl.AddChannelCommand;
import cn.tyrone.payment.channel.service.ChannelRpcService;
import cn.tyrone.payment.sdk.common.pl.Result;
import org.springframework.stereotype.Service;

@Service
public class ChannelAppService {


//    @DubboReference
    ChannelRpcService channelRpcService;

    public Result<Void> addChannel(AddChannelCommand command) {

        return channelRpcService.addChannel(command);

    }
}
