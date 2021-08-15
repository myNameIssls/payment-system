package cn.tyrone.payment.channel.domain.adapter;


import cn.tyrone.payment.channel.common.entity.*;

/**
 * 中金支付支付服务适配器
 */
public interface ICpcnPaymentServiceAdapter extends ICommunalPaymentServiceAdapter, IVirtualAccountPaymentServiceAdapter {

    /**
     * 结算账户维护
     * @param settlementAccountRequest
     * @return
     */
    public SettlementAccountResponse settlementAccount(SettlementAccountRequest settlementAccountRequest);

    /**
     * 查询可代付出金额度[T1018]
     * @param paymentAccountRequest
     * @return
     */
    public PaymentAccountResponse queryPaymentQuota(PaymentAccountRequest paymentAccountRequest);

    /**
     * 中金支付
     * @param EBankRechargeRequest
     * @return
     */
    public EBankRechargeResponse eBankRecharge(EBankRechargeRequest EBankRechargeRequest);

    /**
     * 账户认证申请
     * @param request
     * @return
     */
    public AccountAuthenticationResponse accountAuthenticationApply(AccountAuthenticationRequest request);

    /**
     * 账户认证
     * @param request
     * @return
     */
    public AccountAuthenticationResponse accountAuthentication(AccountAuthenticationRequest request);

    /**
     * 账户明细
     * @param request
     * @return
     */
    public AccountDetailResponse accountDetail(AccountDetailsRequest request);

    /**
     * 账户明细历史
     * @param request
     * @return
     */
    public AccountDetailResponse accountDetailHistory(AccountDetailsRequest request);

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
