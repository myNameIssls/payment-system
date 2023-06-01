package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn;

import cn.tyrone.payment.channelctx.domain.OpenAccountRequest;
import cn.tyrone.payment.channelctx.domain.OpenAccountResponse;
import cn.tyrone.payment.channelctx.domain.route.strategy.OpenAccountRoute;
import org.springframework.stereotype.Service;

@Service
public class OpenAccountRouteCpcnAdapter implements OpenAccountRoute {
    @Override
    public OpenAccountResponse openAccount(OpenAccountRequest openAccountRequest) {
        return null;
    }
}
