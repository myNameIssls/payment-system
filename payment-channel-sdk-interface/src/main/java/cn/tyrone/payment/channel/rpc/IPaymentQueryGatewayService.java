package cn.tyrone.payment.channel.rpc;


import cn.tyrone.payment.channel.common.entity.*;

/**
 * 支付查询网关服务接口
 */
public interface IPaymentQueryGatewayService {

    /**
     * 账户明细
     * @param request
     * @return
     */
    public AccountDetailResponse accountDetail(AccountDetailsRequest request);

    /**
     * 查询可代付出金额度
     * @param paymentAccountRequest
     * @return
     */
    public PaymentAccountResponse queryPaymentQuota(PaymentAccountRequest paymentAccountRequest);

    /**
     * 查询银行列表
     * @return
     */
    public BankQueryResponse queryBankList(BankQueryRequest request);

    /**
     * 查询银行支行信息
     * @param request
     * @return
     */
    public BankBranchQueryResponse queryBankBranch(BankBranchQueryRequest request);


}
