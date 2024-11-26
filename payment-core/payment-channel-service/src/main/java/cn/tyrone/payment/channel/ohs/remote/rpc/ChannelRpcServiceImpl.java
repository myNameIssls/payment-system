package cn.tyrone.payment.channel.ohs.remote.rpc;

import cn.tyrone.payment.channel.domain.channel.ChannelService;
import cn.tyrone.payment.channel.pl.AddChannelCommand;
import cn.tyrone.payment.channel.service.ChannelRpcService;
import cn.tyrone.payment.sdk.common.pl.Result;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
@RequiredArgsConstructor
public class ChannelRpcServiceImpl implements ChannelRpcService {

    private final ChannelService channelService;

    @Override
    public Result<Void> addChannel(AddChannelCommand command) {

        return channelService.addChannel(command);
    }
}
