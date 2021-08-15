package cn.tyrone.payment.channel.ui.rpc;


import cn.tyrone.payment.channel.application.PaymentChannelApplicationService;
import cn.tyrone.payment.channel.common.entity.TransferAccountRequest;
import cn.tyrone.payment.channel.common.entity.TransferAccountResponse;
import cn.tyrone.payment.channel.rpc.IPaymentGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayServiceImpl implements IPaymentGatewayService {

    @Autowired
    private PaymentChannelApplicationService paymentChannelApplicationService;

    /**
     * 转账
     * @param request
     * @return
     */
    @Override
    public TransferAccountResponse transferAccount(TransferAccountRequest request) {

        TransferAccountResponse transferAccountResponse = paymentChannelApplicationService.transferAccount(request);

        return transferAccountResponse;
    }
}
