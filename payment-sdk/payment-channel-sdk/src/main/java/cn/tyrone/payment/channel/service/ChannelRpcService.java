package cn.tyrone.payment.channel.service;

import cn.tyrone.payment.channel.pl.AddChannelCommand;
import cn.tyrone.payment.sdk.common.pl.Result;

public interface ChannelRpcService {

    Result<Void> addChannel(AddChannelCommand command);
}
