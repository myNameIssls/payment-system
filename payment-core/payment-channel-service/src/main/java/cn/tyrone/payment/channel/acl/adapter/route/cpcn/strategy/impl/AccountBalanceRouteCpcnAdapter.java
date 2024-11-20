package cn.tyrone.payment.channel.acl.adapter.route.cpcn.strategy.impl;

import cn.tyrone.payment.channel.acl.adapter.route.cpcn.CpcnApiServiceAdapter;
import cn.tyrone.payment.channel.domain.route.strategy.AccountBalanceRoute;
import cn.tyrone.payment.channel.pl.AccountBalanceRequest;
import cn.tyrone.payment.channel.pl.AccountBalanceResponse;
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
