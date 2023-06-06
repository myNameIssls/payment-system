package cn.tyrone.payment.channelctx.domain.route.strategy;

import cn.tyrone.payment.channelctx.pl.OpenAccountRequest;
import cn.tyrone.payment.channelctx.pl.OpenAccountResponse;

/**
 * 开户路由策略
 */
public interface OpenAccountRoute {

    public OpenAccountResponse openAccount(OpenAccountRequest openAccountRequest);

}
