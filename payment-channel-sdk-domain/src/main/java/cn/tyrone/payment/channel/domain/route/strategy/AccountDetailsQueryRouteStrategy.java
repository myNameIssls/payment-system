package cn.tyrone.payment.channel.domain.route.strategy;

import cn.tyrone.payment.channel.common.entity.AccountDetailResponse;
import cn.tyrone.payment.channel.common.entity.AccountDetailsRequest;

/**
 * 余额查询路由策略
 */
public interface AccountDetailsQueryRouteStrategy {

    /**
     * 账户明细
     * @param request
     * @return
     */
    public AccountDetailResponse accountDetail(AccountDetailsRequest request);

}
