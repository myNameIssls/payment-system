package cn.tyrone.payment.channel.application;

import cn.tyrone.payment.channel.common.entity.*;
import cn.tyrone.payment.channel.domain.adapter.ICpcnPaymentServiceAdapter;
import cn.tyrone.payment.channel.domain.route.strategy.AccountDetailsQueryRouteStrategy;
import cn.tyrone.payment.channel.domain.route.strategy.context.AccountDetailsQueryRouteStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentChannelQueryApplicationService {

    @Autowired
    private ICpcnPaymentServiceAdapter cpcnPaymentServiceAdapter;

    @Autowired
    private AccountDetailsQueryRouteStrategyContext accountDetailsQueryRouteStrategyContext;

    /**
     * 账户明细
     * @param request
     * @return
     */
    public AccountDetailResponse accountDetail(AccountDetailsRequest request) {

        AccountDetailsQueryRouteStrategy accountDetailsQueryRouteStrategy =
                accountDetailsQueryRouteStrategyContext.getAccountDetailsQueryRouteStrategy(request);

        AccountDetailResponse accountDetailResponse = accountDetailsQueryRouteStrategy.accountDetail(request);

        return accountDetailResponse;
    }

    /**
     * 查询可代付出金额度
     * @param paymentAccountRequest
     * @return
     */
    public PaymentAccountResponse queryPaymentQuota(PaymentAccountRequest paymentAccountRequest) {

        PaymentAccountResponse paymentAccountResponse = cpcnPaymentServiceAdapter.queryPaymentQuota(paymentAccountRequest);

        return paymentAccountResponse;
    }

    /**
     * 查询银行列表
     * @return
     */
    public BankQueryResponse queryBankList(BankQueryRequest request) {

        BankQueryResponse bankQueryResponse = cpcnPaymentServiceAdapter.queryBankList(request);

        return bankQueryResponse;
    }

    /**
     * 查询银行支行信息
     * @param request
     * @return
     */
    public BankBranchQueryResponse queryBankBranch(BankBranchQueryRequest request) {

        BankBranchQueryResponse bankBranchQueryResponse = cpcnPaymentServiceAdapter.queryBankBranch(request);

        return bankBranchQueryResponse;
    }

}
