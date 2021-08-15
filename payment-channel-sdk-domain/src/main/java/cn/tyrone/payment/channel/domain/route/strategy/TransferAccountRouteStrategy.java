package cn.tyrone.payment.channel.domain.route.strategy;


import cn.tyrone.payment.channel.common.entity.TransferAccountRequest;
import cn.tyrone.payment.channel.common.entity.TransferAccountResponse;

public interface TransferAccountRouteStrategy {

    /**
     * 转账
     * @param request
     * @return
     */
    TransferAccountResponse transferAccount(TransferAccountRequest request);

}
