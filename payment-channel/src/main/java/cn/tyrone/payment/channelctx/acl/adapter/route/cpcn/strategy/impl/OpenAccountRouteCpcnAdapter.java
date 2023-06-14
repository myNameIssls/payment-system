package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.strategy.impl;

import cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.CpcnApiServiceAdapter;
import cn.tyrone.payment.channelctx.domain.route.strategy.OpenAccountRoute;
import cn.tyrone.payment.channelctx.pl.OpenAccountRequest;
import cn.tyrone.payment.channelctx.pl.OpenAccountResponse;
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
