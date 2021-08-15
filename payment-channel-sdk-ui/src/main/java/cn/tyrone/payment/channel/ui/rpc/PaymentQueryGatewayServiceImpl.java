package cn.tyrone.payment.channel.ui.rpc;


import cn.tyrone.payment.channel.application.PaymentChannelQueryApplicationService;
import cn.tyrone.payment.channel.common.entity.*;
import cn.tyrone.payment.channel.rpc.IPaymentQueryGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付查询网关服务接口
 */
@Service
public class PaymentQueryGatewayServiceImpl implements IPaymentQueryGatewayService {

    @Autowired
    private PaymentChannelQueryApplicationService paymentChannelQueryApplicationService;

    /**
     * 账户明细
     * @param request
     * @return
     */
    @Override
    public AccountDetailResponse accountDetail(AccountDetailsRequest request) {

        AccountDetailResponse accountDetailResponse = paymentChannelQueryApplicationService.accountDetail(request);

        return accountDetailResponse;
    }

    /**
     * 查询可代付出金额度
     * @param paymentAccountRequest
     * @return
     */
    @Override
    public PaymentAccountResponse queryPaymentQuota(PaymentAccountRequest paymentAccountRequest) {

        PaymentAccountResponse paymentAccountResponse = paymentChannelQueryApplicationService.queryPaymentQuota(paymentAccountRequest);

        return paymentAccountResponse;
    }

    /**
     * 查询银行列表
     * @return
     */
    @Override
    public BankQueryResponse queryBankList(BankQueryRequest request) {

        BankQueryResponse bankQueryResponse = paymentChannelQueryApplicationService.queryBankList(request);

        return bankQueryResponse;
    }

    /**
     * 查询银行支行信息
     * @param request
     * @return
     */
    @Override
    public BankBranchQueryResponse queryBankBranch(BankBranchQueryRequest request) {

        BankBranchQueryResponse bankBranchQueryResponse = paymentChannelQueryApplicationService.queryBankBranch(request);

        return bankBranchQueryResponse;
    }
}
