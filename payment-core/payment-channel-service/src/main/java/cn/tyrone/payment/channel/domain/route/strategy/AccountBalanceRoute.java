package cn.tyrone.payment.channel.domain.route.strategy;


import cn.tyrone.payment.channel.pl.AccountBalanceRequest;
import cn.tyrone.payment.channel.pl.AccountBalanceResponse;

/**
 * 账户余额路由
 */
public interface AccountBalanceRoute {

    public AccountBalanceResponse accountBalance(AccountBalanceRequest accountBalanceRequest);

}
