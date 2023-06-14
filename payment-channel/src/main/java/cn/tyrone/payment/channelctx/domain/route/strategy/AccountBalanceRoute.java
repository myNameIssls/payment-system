package cn.tyrone.payment.channelctx.domain.route.strategy;

import cn.tyrone.payment.channelctx.pl.AccountBalanceRequest;
import cn.tyrone.payment.channelctx.pl.AccountBalanceResponse;

/**
 * 账户余额路由
 */
public interface AccountBalanceRoute {

    public AccountBalanceResponse accountBalance(AccountBalanceRequest accountBalanceRequest);

}
