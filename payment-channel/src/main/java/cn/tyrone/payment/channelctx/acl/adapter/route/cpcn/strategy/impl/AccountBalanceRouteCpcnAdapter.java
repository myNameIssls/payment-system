package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.strategy.impl;

import cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.CpcnApiServiceAdapter;
import cn.tyrone.payment.channelctx.domain.route.strategy.AccountBalanceRoute;
import cn.tyrone.payment.channelctx.pl.AccountBalanceRequest;
import cn.tyrone.payment.channelctx.pl.AccountBalanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBalanceRouteCpcnAdapter implements AccountBalanceRoute {

    @Autowired
    private CpcnApiServiceAdapter apiServiceAdapter;

    @Override
    public AccountBalanceResponse accountBalance(AccountBalanceRequest accountBalanceRequest) {

        AccountBalanceResponse accountBalanceResponse = apiServiceAdapter.accountBalance(accountBalanceRequest);

        return accountBalanceResponse;
    }
}
