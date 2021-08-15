package cn.tyrone.payment.channel.infrastructure.api.cpcn.strategy.impl;

import cn.tyrone.payment.channel.common.entity.AccountDetailResponse;
import cn.tyrone.payment.channel.common.entity.AccountDetailsRequest;
import cn.tyrone.payment.channel.domain.adapter.ICpcnPaymentServiceAdapter;
import cn.tyrone.payment.channel.domain.route.strategy.AccountDetailsQueryRouteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户明细查询路由策略实现中金支付
 */
@Service
public class AccountDetailsQueryRouteStrategyCpcnAcs implements AccountDetailsQueryRouteStrategy {

    @Autowired
    private ICpcnPaymentServiceAdapter paymentServiceAdapter;

    /**
     * 账户明细
     * @param request
     * @return
     */
    @Override
    public AccountDetailResponse accountDetail(AccountDetailsRequest request) {

        AccountDetailResponse accountDetailResponse = null;

        boolean ifToday = request.ifToday();

        if (ifToday) {
            accountDetailResponse = paymentServiceAdapter.accountDetail(request);
        }

        if (!ifToday) {
            accountDetailResponse = paymentServiceAdapter.accountDetailHistory(request);
        }

        return accountDetailResponse;
    }

}
