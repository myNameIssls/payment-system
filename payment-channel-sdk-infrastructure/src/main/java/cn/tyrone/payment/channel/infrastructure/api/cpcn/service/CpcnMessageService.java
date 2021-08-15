package cn.tyrone.payment.channel.infrastructure.api.cpcn.service;


import cn.tyrone.payment.channel.common.entity.*;
import cn.tyrone.payment.channel.common.enums.*;
import cn.tyrone.payment.channel.common.valueobject.EnterpriseCorporation;
import cn.tyrone.payment.channel.common.valueobject.EnterpriseCustomer;
import cn.tyrone.payment.channel.common.valueobject.PersonCustomer;
import cn.tyrone.payment.channel.common.valueobject.TransactionParticipant;
import cn.tyrone.payment.channel.infrastructure.api.cpcn.entity.*;
import cn.tyrone.payment.common.util.DateUtil;
import com.trz.netwk.api.trd.*;
import com.trz.netwk.api.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CpcnMessageService {

    private final String SUCCESS_CODE = "000000";

    public TrdT2031Request buildTrdT2031Request(EBankRechargeRequest request) {

        BigDecimal amount = request.getAmount().multiply(new BigDecimal(100));

        String account = request.getAccount();
        String accountName = request.getAccountName();
        AccountPropertyEnum accountPropertyEnum = request.getAccountPropertyEnum();
        SecPayTypeEnum secPayType = accountPropertyEnum.equals(AccountPropertyEnum.ENTERPRISE) ?
                SecPayTypeEnum.ENTERPRISE_E_BANK : SecPayTypeEnum.PERSON_E_BANK;

        TrdT2031Request trdRequest = new TrdT2031Request();
        trdRequest.setMsghd_trdt(DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdRequest.setSrl_ptnsrl(request.getPlatformSerialNumber());
        trdRequest.setCltacc_subno(account);
        trdRequest.setCltacc_cltnm(accountName);
        trdRequest.setBillinfo_aclamt(amount.toString());
        trdRequest.setBillinfo_paytype(PayTypeEnum.E_Bank.getPayType());
        trdRequest.setBillinfo_secpaytype(secPayType.getSecPayType());
        trdRequest.setBillinfo_bankid(request.getBankId());
        trdRequest.setReqflg(ReqFlgEnum.TWO.getFcFlg()); // 发送端标记:0 手机;1PC 端
        trdRequest.setTrsflag(TrsFlagEnum.NORMAL.getTrsFlag());

        return trdRequest;

    }

    public EBankRechargeResponse buildRechargeResponse(TrdT2031Response trdT2031Response) {

        String msghd_rspmsg = trdT2031Response.getMsghd_rspmsg();
        String msghd_rspcode = trdT2031Response.getMsghd_rspcode();
        String srl_ptnsrl = trdT2031Response.getSrl_ptnsrl();
        String srl_platsrl = trdT2031Response.getSrl_platsrl();

        Map<String, Object> map = null;

        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;

        if (msghd_rspcode.equals(SUCCESS_CODE)) {
            communicationStatus = CommunicationStatus.SUCCESS;
            map = new HashMap<>();

            String url = null;
            try {
                url = URLDecoder.decode(trdT2031Response.getUrl(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error("T2031应答报文解析失败: ", e);
                throw new RuntimeException("T2031应答报文解析失败");
            }
            String strs[] = url.split("\\?", 2);
            String actionUrl = strs[0];
            String params[] = strs[1].split("\\&", 2);
            String kvl1[] = params[0].split("\\=", 2);
            String key1 = kvl1[0];
            String val1 = kvl1[1];

            String kvl2[] = params[1].split("\\=", 2);
            String key2 = kvl2[0];
            String val2 = kvl2[1];

            map.put("actionUrl", actionUrl);
            map.put("keyO", key1);
            map.put("valO", val1);
            map.put("keyT", key2);
            map.put("valT", val2);

        }

        EBankRechargeResponse response = EBankRechargeResponse.builder()
                .communicationStatus(communicationStatus)
                .responseMessage(msghd_rspmsg)
                .channelSerialNumber(srl_platsrl)
                .platformSerialNumber(srl_ptnsrl)
                .params(map).build();

        return response;

    }


    public TrdT1001Request buildTrdT1001Request(OpenAccountRequest request) {
        EnterpriseCustomer enterpriseCustomer = request.getEnterpriseCustomer();
        EnterpriseCorporation enterpriseCorporation = enterpriseCustomer.getEnterpriseCorporation();
        CustomerProperty customerProperty = request.getCustomerProperty();
        // 客户性质(0 个人;1 公司)
        String kd = "1";
        String customerName = enterpriseCorporation.getName();
        String idNum = enterpriseCorporation.getCertificateNum();
        String phoneNumber = enterpriseCorporation.getPhoneNumber();
        if (customerProperty.equals(CustomerProperty.PERSONAL)) {
            PersonCustomer personCustomer = request.getPersonCustomer();
            kd = "0";
            customerName = personCustomer.getName();
            idNum = personCustomer.getCertificateNum();
            phoneNumber = personCustomer.getPhoneNumber();
        }
        FcFlgEnum fcFlgEnum = FcFlgEnum.ONE;
        AccountBusinessType accountBusinessType = request.getAccountBusinessType();
        if (accountBusinessType.equals(AccountBusinessType.MODIFY)) {
            fcFlgEnum = FcFlgEnum.TWO;
        } else if (accountBusinessType.equals(AccountBusinessType.DESTORY)) {
            fcFlgEnum = FcFlgEnum.THREE;
        }

        TrdT1001Request trdRequest = new TrdT1001Request();
        trdRequest.setMsghd_trdt(DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdRequest.setSrl_ptnsrl(request.getPlatformSerialNumber());
        trdRequest.setCltacc_cltno(request.getCustomerNo());
        trdRequest.setCltacc_cltnm(request.getAccountName());
        trdRequest.setClt_nm(customerName);
        trdRequest.setClt_kd(kd);
        trdRequest.setClt_cdtp("A"); // 证件类型
        trdRequest.setClt_cdno(idNum);
        trdRequest.setClt_mobno(phoneNumber);
        trdRequest.setFcflg(fcFlgEnum.getFcFlg());
        trdRequest.setAcctp("1");
        if (customerProperty.equals(CustomerProperty.ENTERPRISE)) {
            String unifiedSocialCreditCode = enterpriseCustomer.getUnifiedSocialCreditCode();
            String uscid=unifiedSocialCreditCode.length()==15?unifiedSocialCreditCode+"000":unifiedSocialCreditCode;
            trdRequest.setClt_uscid(uscid);
            trdRequest.setClt_orgcd(uscid);
            trdRequest.setClt_bslic(uscid);
            trdRequest.setClt_swdjh(uscid);
        }

        return trdRequest;

    }

    public OpenAccountResponse buildOpenAccountResponse(TrdT1001Response trdResponse) {

        String msghd_rspcode = trdResponse.getMsghd_rspcode();
        String msghd_rspmsg = trdResponse.getMsghd_rspmsg();
        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;

        String cltacc_subno = null;
        String cltacc_bnkeid = null;
        String cltacc_cltno = null;
        String cltacc_cltnm = null;

        if (msghd_rspcode.equals(SUCCESS_CODE)) {
            communicationStatus = CommunicationStatus.SUCCESS;
            cltacc_subno = trdResponse.getCltacc_subno();
            cltacc_bnkeid = trdResponse.getCltacc_bnkeid();
            cltacc_cltno = trdResponse.getCltacc_cltno();
            cltacc_cltnm = trdResponse.getCltacc_cltnm();
        }

        OpenAccountResponse response = OpenAccountResponse
                .builder()
                .communicationStatus(communicationStatus)
                .responseMessage(msghd_rspmsg)
                .customerNo(cltacc_cltno)
                .virtualAccountName(cltacc_cltnm)
                .virtualAccount(cltacc_subno)
                .bankElecAccount(cltacc_bnkeid)
                .build();

        return response;

    }

    /**
     * 构建资金账户余额查询[T1005]请求对象
     * @param request
     * @return
     */
    public TrdT1005Request buildTrdT1005Request(PaymentAccountRequest request) {
        TrdT1005Request trdRequest = new TrdT1005Request();
        trdRequest.setMsghd_trdt(DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdRequest.setCltacc_subno(request.getAccount());
        trdRequest.setCltacc_cltnm(request.getAccountName());
        return trdRequest;
    }

    /**
     * 构建资金账户余额查询应答对象
     * @param trdT1005Response
     * @return
     */
    public PaymentAccountResponse buildBalanceQueryResponse(TrdT1005Response trdT1005Response) {

        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;
        BigDecimal accountBalance = BigDecimal.ZERO;

        String msghd_rspcode = trdT1005Response.getMsghd_rspcode();
        if (msghd_rspcode.equals(this.SUCCESS_CODE)) {
            communicationStatus = CommunicationStatus.SUCCESS;
            long amt_balamt = trdT1005Response.getAmt_balamt();
            accountBalance = new BigDecimal(amt_balamt).multiply(new BigDecimal("0.01"));
        }

        PaymentAccountResponse response = PaymentAccountResponse.builder()
                .communicationStatus(communicationStatus)
                .accountBalance(accountBalance)
                .build();

        return response;
    }

    /**
     * 出金-申请[T2022]-请求对象
     * @param request
     * @return
     */
    public TrdT2022Request buildTrdT2022Request(WithdrawRequest request) {
        BigDecimal paymentAmount = request.getPaymentAmount();
        paymentAmount = paymentAmount.multiply(new BigDecimal(100));

        TrdT2022Request trdRequest = new TrdT2022Request();
        trdRequest.setMsghd_trdt(DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdRequest.setSrl_ptnsrl(request.getPlatformSerialNumber());
        trdRequest.setCltacc_subno(request.getPayerAccount());
        trdRequest.setCltacc_cltnm(request.getPayerAccountName());
        trdRequest.setBkacc_accno(request.getPayeeAccount());
        trdRequest.setBkacc_accnm(request.getPayeeAccountName());
        trdRequest.setAmt_aclamt(Long.parseLong(paymentAmount.toString()));
        trdRequest.setTrsflag(TrsFlagEnum.NORMAL.getTrsFlag());
        trdRequest.setBalflag(request.getSettlementMethod().getSettlementMethod());
        return trdRequest;

    }

    /**
     * 出金-申请[T2022]-应答对象
     * @param trdCommonResponse
     * @return
     */
    public WithdrawResponse buildWithdrawResponse(TrdCommonResponse trdCommonResponse) {

        CommunicationStatus communicationStatus = CommunicationStatus.SUCCESS;
        String msghd_rspcode = trdCommonResponse.getMsghd_rspcode();
        if (!SUCCESS_CODE.equals(msghd_rspcode)) {
            communicationStatus = CommunicationStatus.FAIL;
        }

        WithdrawResponse withdrawResponse = WithdrawResponse.builder()
                .communicationStatus(communicationStatus)
                .responseMessage(trdCommonResponse.getMsghd_rspmsg())
                .build();

        return withdrawResponse;
    }

    /**
     * 查询可代付出金额度请求对象
     * @param paymentAccountRequest
     * @return
     */
    public TrdT1018Request buildTrdT1018Request(PaymentAccountRequest paymentAccountRequest) {

        TrdT1018Request trdRequest = new TrdT1018Request();
        trdRequest.setMsghd_trdt(DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdRequest.setMsghd_trcd(TransactionCode.T1018.transactionCode);

        trdRequest.setCltacc_subno(paymentAccountRequest.getAccount());
        trdRequest.setCltacc_cltnm(paymentAccountRequest.getAccountName());

        return trdRequest;

    }

    /**
     * 查询可代付出金额度请求对象
     * @param trdT1018Response
     * @return
     */
    public PaymentAccountResponse buildPaymentAccountResponse(TrdT1018Response trdT1018Response) {

        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;
        BigDecimal t0Quota = BigDecimal.ZERO;
        BigDecimal t1Quota = BigDecimal.ZERO;
        BigDecimal accountBalance = BigDecimal.ZERO;
        BigDecimal frozenAmount  = BigDecimal.ZERO;
        BigDecimal usableAmount  = BigDecimal.ZERO;

        String msghd_rspcode = trdT1018Response.getMsghd_rspcode();
        if (msghd_rspcode.equals(this.SUCCESS_CODE)) {
            communicationStatus = CommunicationStatus.SUCCESS;
            long t0amt_ctamta00 = trdT1018Response.getT0amt_ctamta00();
            t0Quota = new BigDecimal(String.valueOf(t0amt_ctamta00));
            long t1amt_ctamta00 = trdT1018Response.getT1amt_ctamta00();
            t1Quota = new BigDecimal(String.valueOf(t1amt_ctamta00));
            long acsamt_balamt = trdT1018Response.getAcsamt_balamt();
            accountBalance = new BigDecimal(String.valueOf(acsamt_balamt));
            long acsamt_useamt = trdT1018Response.getAcsamt_useamt();
            usableAmount = new BigDecimal(String.valueOf(acsamt_useamt));
            long acsamt_frzamt = trdT1018Response.getAcsamt_frzamt();
            frozenAmount = new BigDecimal(String.valueOf(acsamt_frzamt));
        }

        PaymentAccountResponse paymentAccountResponse = PaymentAccountResponse.builder()
                .communicationStatus(communicationStatus).responseMessage(trdT1018Response.getMsghd_rspmsg())
                .t0Quota(t0Quota).t1Quota(t1Quota)
                .accountBalance(accountBalance).usableAmount(usableAmount).frozenAmount(frozenAmount)
                .build();

        return paymentAccountResponse;
    }

    /**
     * 构建订单支付请求对象
     * @param request
     * @return
     */
    public TrdT3004Request buildTrdT3004Request(TransferAccountRequest request) {

        BigDecimal paymentAmount = request.getPaymentAmount().multiply(new BigDecimal(100));

        TransactionParticipant payer = request.getPayer();
        TransactionParticipant payee = request.getPayee();

        TrdT3004Request trdRequest = new TrdT3004Request();
        trdRequest.setMsghd_trdt(DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdRequest.setBillinfo_psubno(payer.getAccount());
        trdRequest.setBillinfo_pnm(payer.getAccountName());
        trdRequest.setBillinfo_rsubno(payee.getAccount());
        trdRequest.setBillinfo_rcltnm(payee.getAccount());
        trdRequest.setBillinfo_aclamt(paymentAmount.longValue());
        trdRequest.setBillinfo_orderno(request.getBusinessOrderNo());
        trdRequest.setBillinfo_billno(request.getPaymentOrderNo());
        trdRequest.setTrsflag(TrsFlagEnum.A00_T3004_T3005.getTrsFlag());

        return trdRequest;

    }

    /**
     * 构建订单支付应答对象
     * @param trdT3004Response
     * @return
     */
    public TransferAccountResponse buildTransferAccountResponse(TrdT3004Response trdT3004Response) {

        String msghd_rspcode = trdT3004Response.getMsghd_rspcode();
        String msghd_rspmsg = trdT3004Response.getMsghd_rspmsg();
        String srl_billno = trdT3004Response.getSrl_billno();
        String srl_platsrl = trdT3004Response.getSrl_platsrl();

        CommunicationStatus communicationStatus = msghd_rspcode.equals(SUCCESS_CODE) ? CommunicationStatus.SUCCESS : CommunicationStatus.FAIL;

        TransferAccountResponse transferAccountResponse = TransferAccountResponse.builder()
                .communicationStatus(communicationStatus).responseMessage(msghd_rspmsg)
                .platformSerialNumber(srl_billno).channelSerialNumber(srl_platsrl)
                .build();

        return transferAccountResponse;
    }

    /**
     * 构建批量订单支付请求对象
     * @param request
     * @return
     */
    public TrdT3005Request buildTrdT3005Request(BatchTransferAccountRequest request) {

        List<TransferAccountRequest> transferAccountRequests = request.getTransferAccountRequests();

        List<PayInfo> payInfos = transferAccountRequests.stream().map(transferAccountRequest -> {
            TransactionParticipant payer = transferAccountRequest.getPayer();
            TransactionParticipant payee = transferAccountRequest.getPayee();
            BigDecimal paymentAmount = transferAccountRequest.getPaymentAmount().multiply(new BigDecimal(100));
            PayInfo payInfo = new PayInfo();
            payInfo.setPsubno(payer.getAccount());
            payInfo.setPnm(payer.getAccountName());
            payInfo.setRsubno(payee.getAccount());
            payInfo.setRcltnm(payee.getAccountName());
            payInfo.setBillno(transferAccountRequest.getPaymentOrderNo());
            payInfo.setOrderno(transferAccountRequest.getBusinessOrderNo());
            payInfo.setAclamt(paymentAmount.longValue());
            payInfo.setTrsflag(TrsFlagEnum.A00_T3004_T3005.getTrsFlag()); // 业务标示:A00 普通订单支付, B00 收款方收款成功后，再 冻结资金, B01 付款方解冻资金后，再 支付给收款方
            return payInfo;

        }).collect(Collectors.toList());

        TrdT3005Request trdT3005Request = new TrdT3005Request();
        trdT3005Request.setPayInfoList(payInfos);

        return trdT3005Request;
    }

    /**
     * 构建批量订单支付应答对象
     * @param trdT3005Response
     * @return
     */
    public BatchTransferAccountResponse buildBatchTransferAccountResponse(TrdT3005Response trdT3005Response) {

        String msghd_rspcode = trdT3005Response.getMsghd_rspcode();
        String msghd_rspmsg = trdT3005Response.getMsghd_rspmsg();
        CommunicationStatus communicationStatus = msghd_rspcode.equals(SUCCESS_CODE) ? CommunicationStatus.SUCCESS : CommunicationStatus.FAIL;


        List<PayRsut> payRsutList = trdT3005Response.getPayRsutList();

        List<TransferAccountResponse> transferAccountResponses = payRsutList.stream().map(payRsut -> {

            String stat = payRsut.getStat();
            String opion = payRsut.getOpion();
            TransactionStatus transactionStatus = TransactionStatus.SUCCESS;
            if (stat.equals(BillStatus.TWO.getBillStatus())) {
                transactionStatus = TransactionStatus.FAILURE;
            }

            return TransferAccountResponse.builder()
                    .channelSerialNumber(payRsut.getPlatsrl())
                    .platformSerialNumber(payRsut.getBillno())
                    .businessOrderNo(payRsut.getOrderno())
                    .transactionStatus(transactionStatus)
                    .transactionResult(opion)
                    .build();

        }).collect(Collectors.toList());

        BatchTransferAccountResponse batchTransferAccountResponse = BatchTransferAccountResponse
                .builder()
                .communicationStatus(communicationStatus).responseMessage(msghd_rspmsg)
                .transferAccountResponses(transferAccountResponses)
                .build();

        return batchTransferAccountResponse;
    }

    /**
     * 结算账户维护请求报文
     * @param request
     * @return
     */
    public TrdT1004Request buildTrdT1004Request(SettlementAccountRequest request) {

        SettlementAccountRequest.BusinessType businessType = request.getBusinessType();
        BankAccountType bankAccountType = request.getBankAccountType();
        CrdTp crdTp = CrdTp.A;
        if (bankAccountType.equals(BankAccountType.TWO)) {
            crdTp = CrdTp.ONE;
        }

        String crsMk = "2";
        String bankBranchCode = request.getBankBranchCode();
        if (bankBranchCode.startsWith(DepositoryBank.BANK_CODE)) {
            crsMk = "1";
        }

        TrdT1004Request trdRequest = new TrdT1004Request();
        trdRequest.setMsghd_trdt(DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdRequest.setSrl_ptnsrl(request.getPlatformSerialNumber());
        trdRequest.setCltacc_subno(request.getAccount());
        trdRequest.setCltacc_cltnm(request.getAccountName());
        trdRequest.setFcflg(businessType.type);

        if (!businessType.equals(SettlementAccountRequest.BusinessType.THREE)) {
            trdRequest.setBkacc_bkid(request.getBankCode());
            trdRequest.setBkacc_accno(request.getBankAccount());
            trdRequest.setBkacc_accnm(request.getBankAccountName());
            trdRequest.setBkacc_acctp(bankAccountType.bankAccountType);
            trdRequest.setBkacc_crdtp(crdTp.crdTp);
            trdRequest.setBkacc_cdtp(request.getCertificateType().type);
            trdRequest.setBkacc_cdno(request.getCertificateNum());
            trdRequest.setBkacc_crsmk(crsMk);
            trdRequest.setBkacc_openbkcd(request.getBankBranchCode());
            trdRequest.setBkacc_openbknm(request.getBankBranchName());
            trdRequest.setBkacc_prccd(request.getBankBranchProCd());
            trdRequest.setBkacc_prcnm(request.getBankBranchProNm());
            trdRequest.setBkacc_citycd(request.getBankBranchCtCd());
            trdRequest.setBkacc_citynm(request.getBankBranchCtNm());
        }

        return trdRequest;

    }

    /**
     * 结算账户维护应答报文
     * @param trdT1004Response
     * @return
     */
    public SettlementAccountResponse buildSettlementAccountResponse(TrdT1004Response trdT1004Response) {

        CommunicationStatus communicationStatus = CommunicationStatus.SUCCESS;
        String msghd_rspcode = trdT1004Response.getMsghd_rspcode();
        if (!SUCCESS_CODE.equals(msghd_rspcode)) {
            communicationStatus = CommunicationStatus.FAIL;
        }

        SettlementAccountResponse settlementAccountResponse = SettlementAccountResponse.builder()
                .communicationStatus(communicationStatus)
                .responseMessage(trdT1004Response.getMsghd_rspmsg())
                .build();
        return settlementAccountResponse;
    }

    /**
     * 打款认证申请请求
     * @param request
     * @return
     */
    public TrdT1131Request buildTrdT1131Request(AccountAuthenticationRequest request) {

        TrdT1131Request trdRequest = new TrdT1131Request();
        trdRequest.setMsghd_trdt(DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdRequest.setBkacc_bkid(request.getBankCode());
        trdRequest.setBkacc_accnm(request.getBankAccountName());
        trdRequest.setBkacc_accno(request.getBankAccount());
        trdRequest.setSrl_ptnsrl(request.getPlatformSerialNumber());
        return trdRequest;

    }

    /**
     * 打款验证申请应答
     * @param trdResponse
     * @return
     */
    public AccountAuthenticationResponse buildAccountAuthenticationResponse(TrdCommonResponse trdResponse) {
        CommunicationStatus communicationStatus = CommunicationStatus.SUCCESS;
        String msghd_rspcode = trdResponse.getMsghd_rspcode();
        if (!SUCCESS_CODE.equals(msghd_rspcode)) {
            communicationStatus = CommunicationStatus.FAIL;
        }
        AccountAuthenticationResponse response = AccountAuthenticationResponse.builder()
                .communicationStatus(communicationStatus)
                .responseMessage(trdResponse.getMsghd_rspmsg())
                .build();
        return response;
    }

    /**
     * 企业账户认证请求
     * @param request
     * @return
     */
    public TrdT1132Request buildTrdT1132Request(AccountAuthenticationRequest request) {

        TrdT1132Request trdRequest = new TrdT1132Request();
        trdRequest.setMsghd_trdt(DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdRequest.setMsghd_trcd(TransactionCode.T1132.transactionCode);
        trdRequest.setSrl_ptnsrl(request.getPlatformSerialNumber());
        BigDecimal amount = request.getAmount();
        BigDecimal a = amount.multiply(new BigDecimal(100));
        BigInteger bigInteger = a.toBigInteger();
        trdRequest.setAmount(Long.parseLong(bigInteger.toString()));
        return trdRequest;

    }

    public AccountAuthenticationResponse buildAccountAuthenticationResponse(TrdT1132Response trdResponse) {

        TransactionStatus transactionStatus = TransactionStatus.FAILURE;
        String transactionMessage = "";
        int count = 0;
        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;
        String msghd_rspcode = trdResponse.getMsghd_rspcode();
        if (msghd_rspcode.equals(this.SUCCESS_CODE)) {
            communicationStatus = CommunicationStatus.SUCCESS;
            String stat = trdResponse.getStat(); // 10=成功，20=失败
            String memo = trdResponse.getMemo();
            String availableVeriCount = trdResponse.getAvailableVeriCount();
            if (!"".equals(availableVeriCount)) {
                count = Integer.parseInt(availableVeriCount);
            }
            if (stat.equals("10")){
                transactionStatus = TransactionStatus.SUCCESS;
            }
            transactionMessage = memo;
        }

        AccountAuthenticationResponse response = AccountAuthenticationResponse.builder()
                .communicationStatus(communicationStatus)
                .responseMessage(trdResponse.getMsghd_rspmsg())
                .transactionStatus(transactionStatus).transactionMessage(transactionMessage)
                .availableVeriCount(count)
                .build();
        return response;
    }

    /**
     * 查询往来账明细(当前日期)[T9007]请求
     * @param request
     * @return
     */
    public TrdT9007Request buildTrdT9007Request(AccountDetailsRequest request) {

        TrdT9007Request trdRequest = new TrdT9007Request();
        trdRequest.setMsghd_trdt(DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdRequest.setDplatsrl(request.getOriginalChannelSerialNumber());
        trdRequest.setSubno(request.getAccount());
        trdRequest.setPagsiz(request.getPageSize());
        trdRequest.setCurpag(request.getCurrentPage());
        trdRequest.setDplatsrl(request.getOriginalChannelSerialNumber());
        trdRequest.setDptnsrl(request.getOriginalPlatformSerialNumber());
        return trdRequest;

    }

    /**
     * 查询往来账明细(当前日期)[T9007]应答
     * @param trdResponse
     * @return
     */
    public AccountDetailResponse buildAccountDetailResponse(TrdT9007Response trdResponse) {

        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;

        List<AccountDetail> accountDetails = null;

        String msghd_rspcode = trdResponse.getMsghd_rspcode();
        if (msghd_rspcode.equals(this.SUCCESS_CODE)) {
            communicationStatus = CommunicationStatus.SUCCESS;

            List<QuyDaT9007> quyDatList = trdResponse.getQuyDatList();

            accountDetails = quyDatList.stream().map(quyDaT9007 -> {

                String dte = quyDaT9007.getDte();
                String tme = quyDaT9007.getTme();
                String datetime = dte + tme;
                LocalDateTime localDateTime = DateUtil.getLocalDateTime(datetime, DateUtil.Pattern.NORM_DATETIME_CLOSE_PATTERN);

                long balamt = quyDaT9007.getBalamt();
                BigDecimal accountBalance = new BigDecimal(balamt).multiply(new BigDecimal(0.01)).setScale(2, RoundingMode.HALF_UP);

                String tye = quyDaT9007.getTye();
                PayFlowDirection payFlowDirection = tye.equals("C") ? PayFlowDirection.IN : PayFlowDirection.OUT;

                long feeamt = quyDaT9007.getFeeamt();
                BigDecimal feeAmount = new BigDecimal(feeamt).multiply(new BigDecimal(0.01)).setScale(2, RoundingMode.HALF_UP);

                long aclamt = quyDaT9007.getAclamt();
                BigDecimal transactionAmount = new BigDecimal(aclamt)
                        .multiply(new BigDecimal(0.01)).setScale(2, RoundingMode.HALF_UP);

                return AccountDetail.builder()
                        .transactionDateTime(localDateTime)
                        .account(quyDaT9007.getSubno())
                        .payFlowDirection(payFlowDirection)
                        .oppositeAccount(quyDaT9007.getRsubno()).oppositeAccountName(quyDaT9007.getRcltnm())
                        .feeAmount(feeAmount)
                        .transactionAmount(transactionAmount)
                        .accountBalance(accountBalance)
                        .originalChannelSerialNumber(quyDaT9007.getDplatsrl())
                        .originalPlatformSerialNumber(quyDaT9007.getDptnsrl())
                        .postscript(quyDaT9007.getUsage())
                        .build();
            }).collect(Collectors.toList());

        }

        AccountDetailResponse accountDetailResponse = AccountDetailResponse.builder()
                .communicationStatus(communicationStatus).responseMessage(trdResponse.getMsghd_rspmsg())
                .totalPage(trdResponse.getTalpag()).totalRecords((int) trdResponse.getTalrcd())
                .accountDetails(accountDetails)
                .build();

        return accountDetailResponse;
    }

    /**
     * 查询往来账明细(当前日期)[T9008]请求
     * @param request
     * @return
     */
    public TrdT9008Request buildTrdT9008Request(AccountDetailsRequest request) {
        TrdT9008Request trdRequest = new TrdT9008Request();
        trdRequest.setMsghd_trdt(DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdRequest.setQuystdt(DateUtil.getLocalDate(request.getStartDate(), DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdRequest.setQuyenddt(DateUtil.getLocalDate(request.getEndDate(), DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT));
        trdRequest.setSubno(request.getAccount());
        trdRequest.setPagsiz(request.getPageSize());
        trdRequest.setCurpag(request.getCurrentPage());
        trdRequest.setDplatsrl(request.getOriginalChannelSerialNumber());
        trdRequest.setDptnsrl(request.getOriginalPlatformSerialNumber());
        return trdRequest;
    }

    /**
     * 查询往来账明细(当前日期)[T9008]应答
     * @param trdResponse
     * @return
     */
    public AccountDetailResponse buildAccountDetailResponse(TrdT9008Response trdResponse) {

        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;

        List<AccountDetail> accountDetails = null;

        String msghd_rspcode = trdResponse.getMsghd_rspcode();
        if (msghd_rspcode.equals(this.SUCCESS_CODE)) {
            communicationStatus = CommunicationStatus.SUCCESS;

            List<QuyDaT9008> quyDatList = trdResponse.getQuyDatList();

            accountDetails = quyDatList.stream().map(quyDaT9008 -> {

                String dte = quyDaT9008.getDte();
                String tme = quyDaT9008.getTme();
                String datetime = dte + tme;
                LocalDateTime localDateTime = DateUtil.getLocalDateTime(datetime, DateUtil.Pattern.NORM_DATETIME_CLOSE_PATTERN);

                long balamt = quyDaT9008.getBalamt();
                BigDecimal accountBalance = new BigDecimal(balamt).multiply(new BigDecimal(0.01)).setScale(2, RoundingMode.HALF_UP);

                String tye = quyDaT9008.getTye();
                PayFlowDirection payFlowDirection = tye.equals("C") ? PayFlowDirection.IN : PayFlowDirection.OUT;

                long feeamt = quyDaT9008.getFeeamt();
                BigDecimal feeAmount = new BigDecimal(feeamt).multiply(new BigDecimal(0.01)).setScale(2, RoundingMode.HALF_UP);

                long aclamt = quyDaT9008.getAclamt();
                BigDecimal transactionAmount = new BigDecimal(aclamt).multiply(new BigDecimal(0.01)).setScale(2, RoundingMode.HALF_UP);

                return AccountDetail.builder()
                        .transactionDateTime(localDateTime)
                        .account(quyDaT9008.getSubno())
                        .payFlowDirection(payFlowDirection)
                        .oppositeAccount(quyDaT9008.getRsubno()).oppositeAccountName(quyDaT9008.getRcltnm())
                        .feeAmount(feeAmount)
                        .transactionAmount(transactionAmount)
                        .accountBalance(accountBalance)
                        .originalChannelSerialNumber(quyDaT9008.getDplatsrl())
                        .originalPlatformSerialNumber(quyDaT9008.getDptnsrl())
                        .postscript(quyDaT9008.getUsage())
                        .build();
            }).collect(Collectors.toList());

        }

        AccountDetailResponse accountDetailResponse = AccountDetailResponse.builder()
                .communicationStatus(communicationStatus).responseMessage(trdResponse.getMsghd_rspmsg())
                .totalPage(trdResponse.getTalpag()).totalRecords((int) trdResponse.getTalrcd())
                .currentPage((int)trdResponse.getCurpag())
                .accountDetails(accountDetails)
                .build();

        return accountDetailResponse;


    }

    public BankQueryResponse buildBankInfoQueryResponse(TrdT1008Response trdResponse) {

        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;

        List<BankChannelDTO> bankChannelDTOS = null;

        String msghd_rspcode = trdResponse.getMsghd_rspcode();
        if (msghd_rspcode.equals(this.SUCCESS_CODE)) {
            communicationStatus = CommunicationStatus.SUCCESS;

            List<BankInfo> bankList = trdResponse.getBankList();

            bankChannelDTOS = bankList.stream().map(bankInfo -> {
                return BankChannelDTO.builder()
                        .bankId(bankInfo.getBkid())
                        .bankName(bankInfo.getBknm())
                        .build();
            }).collect(Collectors.toList());
        }

        BankQueryResponse bankQueryResponse = BankQueryResponse.builder()
                .communicationStatus(communicationStatus)
                .bankChannelDTOS(bankChannelDTOS)
                .build();
        return bankQueryResponse;
    }


    public BankBranchQueryResponse buildBankBranchQueryResponse(TrdT1017Response trdResponse) {

        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;

        List<BankBranchBranchDTO> bankBranchBranchDTOS = null;

        String msghd_rspcode = trdResponse.getMsghd_rspcode();
        if (msghd_rspcode.equals(this.SUCCESS_CODE)) {
            communicationStatus = CommunicationStatus.SUCCESS;
            List<PayBnk> payBnkList = trdResponse.getPayBnkList();

            bankBranchBranchDTOS = payBnkList.stream().map(payBnk -> {
                return BankBranchBranchDTO.builder()
                        .cityId(payBnk.getCitycd())
                        .bankId(payBnk.getBkid())
                        .bankBranchId(payBnk.getOpenbkcd())
                        .bankBranchName(payBnk.getOpenbknm())
                        .build();
            }).collect(Collectors.toList());

        }

        BankBranchQueryResponse bankBranchQueryResponse = BankBranchQueryResponse.builder()
                .communicationStatus(communicationStatus)
                .bankBranchBranchDTOS(bankBranchBranchDTOS)
                .build();
        return bankBranchQueryResponse;
    }
}
