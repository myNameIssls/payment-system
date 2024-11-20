package cn.tyrone.payment.channel.acl.adapter.route.cpcn.strategy.impl;

import cn.tyrone.payment.channel.acl.adapter.route.cpcn.CpcnApiServiceAdapter;
import cn.tyrone.payment.channel.domain.route.strategy.OpenAccountRoute;

import cn.tyrone.payment.channel.pl.OpenAccountRequest;
import cn.tyrone.payment.channel.pl.OpenAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenAccountRouteCpcnAdapter implements OpenAccountRoute {


    @Autowired
    private CpcnApiServiceAdapter apiServiceAdapter;

    @Override
    public OpenAccountResponse openAccount(OpenAccountRequest openAccountRequest) {

        OpenAccountResponse openAccountResponse = apiServiceAdapter.openAccount(openAccountRequest);

        return openAccountResponse;
    }
}
