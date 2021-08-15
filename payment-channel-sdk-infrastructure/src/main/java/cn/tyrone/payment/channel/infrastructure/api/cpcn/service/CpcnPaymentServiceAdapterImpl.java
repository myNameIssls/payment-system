package cn.tyrone.payment.channel.infrastructure.api.cpcn.service;


import cn.tyrone.payment.channel.common.entity.*;
import cn.tyrone.payment.channel.common.enums.SettlementMethod;
import cn.tyrone.payment.channel.domain.adapter.ICpcnPaymentServiceAdapter;
import cn.tyrone.payment.common.util.DateUtil;
import com.trz.netwk.api.trd.*;
import com.trz.netwk.api.vo.BankAccT1007;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class CpcnPaymentServiceAdapterImpl implements ICpcnPaymentServiceAdapter {

    @Autowired
    private CpcnMessageService messageService;
    @Autowired
    private CpcnPaymentService paymentService;

    /**
     * 开户
     * @param request
     * @return
     */
    @Override
    public OpenAccountResponse openAccount(OpenAccountRequest request) {

        TrdT1001Request trdT1001Request = messageService.buildTrdT1001Request(request);
        TrdT1001Response trdResponse = paymentService.t1001(trdT1001Request);
        OpenAccountResponse response = messageService.buildOpenAccountResponse(trdResponse);

        return response;
    }

    /**
     * 结算账户
     * @param settlementAccountRequest
     * @return
     */
    @Override
    public SettlementAccountResponse settlementAccount(SettlementAccountRequest settlementAccountRequest) {

        SettlementAccountRequest.BusinessType businessType = settlementAccountRequest.getBusinessType();

        if (businessType == null ||
                (!businessType.equals(SettlementAccountRequest.BusinessType.THREE))) {
            TrdT1007Request trdT1007Request = new TrdT1007Request();
            trdT1007Request.setCltacc_subno(settlementAccountRequest.getAccount());
            trdT1007Request.setCltacc_cltnm(settlementAccountRequest.getAccountName());
            TrdT1007Response trdT1007Response = paymentService.t1007(trdT1007Request);
            List<BankAccT1007> bankAccList = trdT1007Response.getBankAccList();
            SettlementAccountRequest.BusinessType businessTypeT = SettlementAccountRequest.BusinessType.ONE;
            if (bankAccList.size() >= 1) {
                businessTypeT = SettlementAccountRequest.BusinessType.TWO;
            }
            settlementAccountRequest.setBusinessType(businessTypeT);
        }

        TrdT1004Request trdT1004Request = messageService.buildTrdT1004Request(settlementAccountRequest);
        TrdT1004Response trdT1004Response = paymentService.t1004(trdT1004Request);
        SettlementAccountResponse response = messageService.buildSettlementAccountResponse(trdT1004Response);

        return response;
    }

    /**
     * 资金账户余额查询
     * @param request
     * @return
     */
    @Override
    public PaymentAccountResponse balanceQuery(PaymentAccountRequest request) {
        TrdT1005Request trdT1005Request = messageService.buildTrdT1005Request(request);
        TrdT1005Response trdT1005Response = paymentService.t1005(trdT1005Request);
        PaymentAccountResponse response = messageService.buildBalanceQueryResponse(trdT1005Response);
        return response;
    }

    /**
     * 订单支付
     * @param request
     * @return
     */
    @Override
    public TransferAccountResponse transferAccount(TransferAccountRequest request) {
        TrdT3004Request trdT3004Request = messageService.buildTrdT3004Request(request);
        TrdT3004Response trdT3004Response = paymentService.t3004(trdT3004Request);
        TransferAccountResponse transferAccountResponse = messageService.buildTransferAccountResponse(trdT3004Response);
        return transferAccountResponse;
    }

    /**
     * 批量订单支付
     * @param request
     * @return
     */
    @Override
    public BatchTransferAccountResponse batchTransferAccount(BatchTransferAccountRequest request) {
        TrdT3005Request trdT3005Request = messageService.buildTrdT3005Request(request);
        TrdT3005Response trdT3005Response = paymentService.t3005(trdT3005Request);
        BatchTransferAccountResponse batchTransferAccountResponse = messageService.buildBatchTransferAccountResponse(trdT3005Response);
        return batchTransferAccountResponse;
    }

    /**
     * 查询可代付出金额度
     * @param paymentAccountRequest
     * @return
     */
    @Override
    public PaymentAccountResponse queryPaymentQuota(PaymentAccountRequest paymentAccountRequest) {

        TrdT1018Request trdT1018Request = messageService.buildTrdT1018Request(paymentAccountRequest);
        TrdT1018Response trdT1018Response = paymentService.t1018(trdT1018Request);
        PaymentAccountResponse paymentAccountResponse = messageService.buildPaymentAccountResponse(trdT1018Response);

        return paymentAccountResponse;
    }

    /**
     * 出金-申请[T2022]
     * @param request
     * @return
     */
    @Override
    public WithdrawResponse withdraw(WithdrawRequest request) {

        PaymentAccountRequest paymentAccountRequest = PaymentAccountRequest.builder()
                .account(request.getPayerAccount())
                .accountName(request.getPayerAccountName())
                .build();
        PaymentAccountResponse paymentAccountResponse = this.queryPaymentQuota(paymentAccountRequest);

        BigDecimal t0Quota = paymentAccountResponse.getT0Quota();
        BigDecimal paymentAmount = request.getPaymentAmount();
        BigDecimal paymentAmountTemp = paymentAmount.multiply(new BigDecimal(100));

        SettlementMethod settlementMethod = paymentAmountTemp.compareTo(t0Quota) <= 0 ? SettlementMethod.T0 : SettlementMethod.T1;
        request.setSettlementMethod(settlementMethod);
        TrdT2022Request trdT2022Request = messageService.buildTrdT2022Request(request);
        TrdCommonResponse trdCommonResponse = paymentService.t2022(trdT2022Request);
        WithdrawResponse withdrawResponse = messageService.buildWithdrawResponse(trdCommonResponse);

        return withdrawResponse;
    }

    @Override
    public TransactionStatusQueryResponse transactionStatusQuery(TransactionStatusQueryRequest request) {
        return null;
    }

    /**
     * 网银充值
     * @param request
     * @return
     */
    @Override
    public EBankRechargeResponse eBankRecharge(EBankRechargeRequest request) {

        TrdT2031Request trdT2031Request = messageService.buildTrdT2031Request(request);
        TrdT2031Response trdT2031Response = paymentService.t2031(trdT2031Request);
        EBankRechargeResponse response = messageService.buildRechargeResponse(trdT2031Response);

        return response;
    }

    /**
     * 账户认证申请
     * @param request
     * @return
     */
    @Override
    public AccountAuthenticationResponse accountAuthenticationApply(AccountAuthenticationRequest request) {
        TrdT1131Request trdT1131Request = messageService.buildTrdT1131Request(request);
        TrdCommonResponse trdResponse = paymentService.t1131(trdT1131Request);
        AccountAuthenticationResponse response = messageService.buildAccountAuthenticationResponse(trdResponse);
        return response;
    }

    /**
     * 账户认证
     * @param request
     * @return
     */
    @Override
    public AccountAuthenticationResponse accountAuthentication(AccountAuthenticationRequest request) {
        TrdT1132Request trdT1132Request = messageService.buildTrdT1132Request(request);
        TrdT1132Response trdResponse = paymentService.t1132(trdT1132Request);
        AccountAuthenticationResponse response = messageService.buildAccountAuthenticationResponse(trdResponse);
        return response;
    }

    /**
     * 账户明细
     * @param request
     * @return
     */
    @Override
    public AccountDetailResponse accountDetail(AccountDetailsRequest request) {

        TrdT9007Request trdT9007Request = messageService.buildTrdT9007Request(request);
        TrdT9007Response trdResponse = paymentService.t9007(trdT9007Request);
        AccountDetailResponse accountDetailResponse = messageService.buildAccountDetailResponse(trdResponse);

        return accountDetailResponse;
    }

    /**
     * 账户明细历史
     * @param request
     * @return
     */
    @Override
    public AccountDetailResponse accountDetailHistory(AccountDetailsRequest request) {

        TrdT9008Request trdRequest = messageService.buildTrdT9008Request(request);
        TrdT9008Response trdResponse = paymentService.t9008(trdRequest);
        AccountDetailResponse accountDetailResponse = messageService.buildAccountDetailResponse(trdResponse);

        return accountDetailResponse;
    }

    /**
     * 查询银行列表
     * @return
     */
    @Override
    public BankQueryResponse queryBankList(BankQueryRequest request) {

        TrdT1008Request trdT1008Request = new TrdT1008Request();
        trdT1008Request.setMsghd_trdt(DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdT1008Request.setQryflag(request.getBankQueryFlag().flag);

        TrdT1008Response trdT1008Response = paymentService.t1008(trdT1008Request);
        BankQueryResponse bankQueryResponse = messageService.buildBankInfoQueryResponse(trdT1008Response);

        return bankQueryResponse;
    }

    /**
     * 查询银行支行信息
     * @param request
     * @return
     */
    @Override
    public BankBranchQueryResponse queryBankBranch(BankBranchQueryRequest request) {

        TrdT1017Request trdT1017Request = new TrdT1017Request();
        trdT1017Request.setMsghd_trdt(DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdT1017Request.setQryflag(request.getBankQueryFlag().flag);
        trdT1017Request.setBkid(request.getBankId());
        trdT1017Request.setCitycd(request.getCityId());
        trdT1017Request.setQuerynum("500");

        TrdT1017Response trdT1017Response = paymentService.t1017(trdT1017Request);
        BankBranchQueryResponse bankBranchQueryResponse = messageService.buildBankBranchQueryResponse(trdT1017Response);

        return bankBranchQueryResponse;
    }
}
