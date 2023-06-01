package cn.tyrone.payment.channelctx.domain.route.strategy;

import cn.tyrone.payment.channelctx.domain.OpenAccountRequest;
import cn.tyrone.payment.channelctx.domain.OpenAccountResponse;

/**
 * 开户路由策略
 */
public interface OpenAccountRoute {

    public OpenAccountResponse openAccount(OpenAccountRequest openAccountRequest);

}
