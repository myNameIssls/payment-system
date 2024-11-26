package cn.tyrone.payment.console.ohs.remote.controller;


import cn.tyrone.payment.channel.pl.AddChannelCommand;
import cn.tyrone.payment.console.ohs.local.ChannelAppService;
import cn.tyrone.payment.sdk.common.pl.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/channel")
@RequiredArgsConstructor
public class ChannelCtrl {

    private final ChannelAppService channelAppService;

    /**
     * 添加渠道
     * @param command
     * @return
     */
    public Result<Void> addChannel(AddChannelCommand command) {

        return channelAppService.addChannel(command);
    }

}
