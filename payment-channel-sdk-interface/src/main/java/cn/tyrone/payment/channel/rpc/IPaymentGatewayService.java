package cn.tyrone.payment.channel.rpc;


import cn.tyrone.payment.channel.common.entity.TransferAccountRequest;
import cn.tyrone.payment.channel.common.entity.TransferAccountResponse;

public interface IPaymentGatewayService {

    /**
     * 转账
     * @param request
     * @return
     */
    TransferAccountResponse transferAccount(TransferAccountRequest request);

}
