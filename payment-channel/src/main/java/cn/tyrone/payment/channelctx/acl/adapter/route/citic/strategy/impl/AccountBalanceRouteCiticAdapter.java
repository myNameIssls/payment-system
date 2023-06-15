package cn.tyrone.payment.channelctx.acl.adapter.route.citic.strategy.impl;

import cn.tyrone.payment.channelctx.acl.adapter.route.citic.CiticApiServiceAdapter;
import cn.tyrone.payment.channelctx.domain.route.strategy.AccountBalanceRoute;
import cn.tyrone.payment.channelctx.pl.AccountBalanceRequest;
import cn.tyrone.payment.channelctx.pl.AccountBalanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBalanceRouteCiticAdapter implements AccountBalanceRoute {

    @Autowired
    private CiticApiServiceAdapter apiServiceAdapter;

    @Override
    public AccountBalanceResponse accountBalance(AccountBalanceRequest accountBalanceRequest) {

        AccountBalanceResponse accountBalanceResponse = apiServiceAdapter.accountBalance(accountBalanceRequest);

        return accountBalanceResponse;
    }
}
