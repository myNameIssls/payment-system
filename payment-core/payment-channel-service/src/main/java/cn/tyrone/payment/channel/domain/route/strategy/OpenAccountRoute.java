package cn.tyrone.payment.channel.domain.route.strategy;


import cn.tyrone.payment.channel.pl.OpenAccountRequest;
import cn.tyrone.payment.channel.pl.OpenAccountResponse;

/**
 * 开户路由策略
 */
public interface OpenAccountRoute {

    public OpenAccountResponse openAccount(OpenAccountRequest openAccountRequest);

}
