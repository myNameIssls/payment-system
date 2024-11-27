package cn.tyrone.payment.console.ohs.remote.controller;


import cn.tyrone.payment.channel.pl.AddChannelCommand;
import cn.tyrone.payment.console.ohs.local.ChannelAppService;
import cn.tyrone.payment.sdk.common.pl.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制台应用/渠道管理
 */
@RestController()
@RequestMapping("/channel")
@RequiredArgsConstructor
public class ChannelCtrl {

    private final ChannelAppService channelAppService;

    /**
     * 添加渠道
     * @param command
     * @return
     */
    @PostMapping("/add")
    public Result<Void> addChannel(AddChannelCommand command) {

        return channelAppService.addChannel(command);
    }

}
