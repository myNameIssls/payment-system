package cn.tyrone.payment.channel.infrastructure.api.citic.service;


import cn.tyrone.payment.channel.common.entity.*;
import cn.tyrone.payment.channel.common.enums.CommunicationStatus;
import cn.tyrone.payment.channel.common.enums.PaymentChannelConfig;
import cn.tyrone.payment.channel.common.enums.TransactionStatus;
import cn.tyrone.payment.channel.common.valueobject.Contact;
import cn.tyrone.payment.channel.common.valueobject.EnterpriseCustomer;
import cn.tyrone.payment.channel.common.valueobject.TransactionParticipant;
import cn.tyrone.payment.channel.infrastructure.api.citic.entity.*;
import cn.tyrone.payment.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CiticMessageService {

    /**
     * 构建会员注册请求报文对象
     *
     * @param request
     * @return
     */
    public DlbregsnRequest buildDlbregsnRequest(OpenAccountRequest request) {

        EnterpriseCustomer enterpriseInfo = request.getEnterpriseCustomer();
        List<Contact> contactInfos = request.getContactInfos();
        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        Map<String, Object> channelConfig = paymentChannelConfig.getChannelConfig();
        String userName = String.valueOf(channelConfig.get("user_name"));
        String mainAccNo = String.valueOf(channelConfig.get("main_acc_no"));

        List<VilcstData> vilcstDataList = contactInfos.stream().map(contactInfo -> {
            return VilcstData.builder()
                    .contactName(contactInfo.getContactName())
                    .contactPhone(contactInfo.getContactPhone())
                    .mailAddress(contactInfo.getMailAddress())
                    .build();
        }).collect(Collectors.toList());

        DlbregsnRequest dlbregsnRequest = DlbregsnRequest.builder()
                .citicAction(CiticAction.DLBREGSN)
                .userName(userName).mainAccNo(mainAccNo)
                .appFlag(AppFlag.B2B).accGenType(AccGenType.AUTO_INPUT)
                .subAccNo("").subAccNm(request.getAccountName())
                .accType(AccType.COMMON).calInterestFlag(CalInterestFlag.ZERO).interestRate("")
                .overFlag(OverFlag.ZERO).overAmt("").overRate("")
                .autoAssignInterestFlag(AutoAssignInterestFlag.ONE).autoAssignTranFeeFlag(AutoAssignTranFeeFlag.ONE)
                .feeType(FeeType.ZERO).realNameParm(RealNameParm.ZERO).subAccPrintParm(SubAccPrintParm.ONE)
                .vtlCustNm(enterpriseInfo.getEnterpriseName()).legalPersonNm(enterpriseInfo.getEnterpriseCorporation().getName())
                .custCertType(CustCertType.D).custCertNo(enterpriseInfo.getUnifiedSocialCreditCode())
                .commAddress(enterpriseInfo.getAddress())
                .vilcstDataList(vilcstDataList)
                .mngNode("211101").branch("001")
                .build();

        dlbregsnRequest.setCiticAction(CiticAction.DLBREGSN);

        return dlbregsnRequest;
    }


    /**
     * 根据会员注册应答报文对象构建开户对象
     *
     * @param response
     * @return
     */
    public OpenAccountResponse buildOpenAccountResponse(DlbregsnResponse response) {

        CiticStatus citicStatus = response.getCiticStatus();
        CommunicationStatus communicationStatus = citicStatus.equals(CiticStatus.AAAAAAA) ? CommunicationStatus.SUCCESS : CommunicationStatus.FAIL;

        OpenAccountResponse openAccountResponse = OpenAccountResponse.builder()
                .communicationStatus(communicationStatus)
                .responseMessage(citicStatus.getStatusText())
                .virtualAccount(response.getSubAccNo())
                .virtualAccountName(response.getSubAccNm())
                .customerNo(response.getHostNo())
                .build();

        return openAccountResponse;
    }

    /**
     * 构建余额查询请求报文对象
     *
     * @param request
     * @return
     */
    public DlsbalqrRequest buildDlsbalqrRequest(PaymentAccountRequest request) {

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        Map<String, Object> channelConfig = paymentChannelConfig.getChannelConfig();
        String userName = String.valueOf(channelConfig.get("user_name"));
        String mainAccNo = String.valueOf(channelConfig.get("main_acc_no"));

        DlsbalqrRequest dlsbalqrRequest = DlsbalqrRequest.builder()
                .citicAction(CiticAction.DLSBALQR)
                .userName(userName)
                .mainAccNo(mainAccNo)
                .subAccNo(request.getAccount())
                .build();
        return dlsbalqrRequest;
    }

    /**
     * 构建余额查询应答报文对象
     *
     * @param dlsbalqrResponse
     * @return
     */
    public PaymentAccountResponse buildBalanceQueryResponse(DlsbalqrResponse dlsbalqrResponse) {

        DlsbalqrResponseUserData userDataResponse = dlsbalqrResponse.getUserDataList().get(0);
        String kyAmt = userDataResponse.getKyAmt();

        BigDecimal accountBalance = new BigDecimal(kyAmt);

        PaymentAccountResponse paymentAccountResponse = PaymentAccountResponse.builder()
                .accountBalance(accountBalance)
                .build();

        return paymentAccountResponse;
    }

    /**
     * 构建强制转账请求报文对象
     *
     * @param request
     * @return
     */
    public DlmdetrnRequest buildDlmdetrnRequest(TransferAccountRequest request) {

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        Map<String, Object> channelConfig = paymentChannelConfig.getChannelConfig();
        String userName = String.valueOf(channelConfig.get("user_name"));
        String mainAccNo = String.valueOf(channelConfig.get("main_acc_no"));

        TransactionParticipant payer = request.getPayer();
        TransactionParticipant payee = request.getPayee();

        DlmdetrnRequest dlmdetrnRequest = DlmdetrnRequest.builder()
                .citicAction(CiticAction.DLMDETRN)
                .userName(userName)
                .mainAccNo(mainAccNo)
                .clientID(request.getPlatformSerialNumber())
                .payAccNo(payer.getAccount())
                .tranType(TranType.BF)
                .recvAccNo(payee.getAccount())
                .recvAccNm(payee.getAccountName())
                .tranAmt(request.getPaymentAmount().toString())
                .freezeNo("").ofreezeamt("")
                .memo(request.getPostscript())
                .tranFlag(TranFlag.ONE)
                .build();
        return dlmdetrnRequest;
    }

    /**
     * 构建强制转账应答报文对象
     *
     * @param dlmdetrnResponse
     * @return
     */
    public TransferAccountResponse buildTransferAccountResponse(DlmdetrnResponse dlmdetrnResponse) {

        CiticStatus citicStatus = dlmdetrnResponse.getCiticStatus();
        TransactionStatus transactionStatus = citicStatus.equals(CiticStatus.AAAAAAA) ? TransactionStatus.SUCCESS : TransactionStatus.FAILURE;
        transactionStatus.setDescribe(citicStatus.getStatusText());

        TransferAccountResponse transferAccountResponse = TransferAccountResponse.builder()
                .transactionStatus(transactionStatus)
                .build();

        return transferAccountResponse;
    }

    /**
     * 平台出金
     *
     * @param request
     * @return
     */
    public DlintfcsRequest buildDlintfcsRequest(WithdrawRequest request) {

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        Map<String, Object> channelConfig = paymentChannelConfig.getChannelConfig();
        String userName = String.valueOf(channelConfig.get("user_name"));

        List<WithdrawRequest> withdrawRequests = request.getWithdrawRequests();

        List<DlintfcsRequestUserData> userDataList = withdrawRequests.stream().map(withdrawRequest -> {

            String payeeBankNumber = withdrawRequest.getPayeeBankNumber();

            DlintfcsRequestUserData dlintfcsRequestUserData = DlintfcsRequestUserData.builder()
                    .clientID(withdrawRequest.getPlatformSerialNumber())
                    .accountNo(withdrawRequest.getPayerAccount())
                    .recvAccNo(withdrawRequest.getPayeeAccount())
                    .recvAccNm(withdrawRequest.getPayeeAccountName())
                    .tranAmt(withdrawRequest.getPaymentAmount())
                    .payFlg(PayFlg.ZERO)
                    .recvTgfi(payeeBankNumber)
                    .recvBankNm("")
                    .memo("")
                    .preFlg(PreFlg.ZERO)
                    .preDate("")
                    .preTime(null)
                    .field("")
                    .build();
            dlintfcsRequestUserData.setSameBank(dlintfcsRequestUserData.ifSameBank(payeeBankNumber));

            return dlintfcsRequestUserData;
        }).collect(Collectors.toList());

        DlintfcsRequest dlintfcsRequest = DlintfcsRequest.builder()
                .citicAction(CiticAction.DLINTFCS)
                .userName(userName)
                .totalNumber(1)
                .totalAmount(request.getPaymentAmount())
                .userDataList(userDataList)
                .build();

        return dlintfcsRequest;

    }

    /**
     * 平台出金应答
     *
     * @param dlintfcsResponse
     * @return
     */
    public WithdrawResponse buildWithdrawResponse(DlintfcsResponse dlintfcsResponse) {

        List<DlintfcsResponseUserData> userDataList = dlintfcsResponse.getUserDataList();

        List<WithdrawResponse> withdrawResponses = userDataList.stream().map(dlintfcsResponseUserData -> {

            TransactionStatus transactionStatus = TransactionStatus.SUCCESS;
            CiticStatus citicStatus = dlintfcsResponseUserData.getCiticStatus();
            if (!citicStatus.equals(CiticStatus.AAAAAAA)) {
                transactionStatus = TransactionStatus.FAILURE;
            }

            return WithdrawResponse.builder()
                    .platformSerialNumber(dlintfcsResponseUserData.getClientID())
                    .channelSerialNumber(dlintfcsResponseUserData.getClientID())
                    .transactionStatus(transactionStatus)
                    .responseMessage(citicStatus.getStatusText())
                    .build();

        }).collect(Collectors.toList());

        WithdrawResponse withdrawResponse = WithdrawResponse.builder().withdrawResponses(withdrawResponses).build();

        return withdrawResponse;

    }

    /**
     * 交易状态查询请求
     * @param request
     * @return
     */
    public DlcidsttRequest buildDlcidsttRequest(TransactionStatusQueryRequest request) {

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        Map<String, Object> channelConfig = paymentChannelConfig.getChannelConfig();
        String userName = String.valueOf(channelConfig.get("user_name"));

        DlcidsttRequest dlcidsttRequest = DlcidsttRequest.builder()
                .paymentChannelConfig(paymentChannelConfig)
                .userName(userName)
                .clientID(request.getPlatformSerialNumber())
                .type("")
                .build();
        return dlcidsttRequest;
    }

    /**
     * 交易状态查询应答
     * @param dlcidsttResponse
     * @return
     */
    public TransactionStatusQueryResponse buildTransactionStatusQueryResponse(DlcidsttResponse dlcidsttResponse) {

        CiticStatus citicStatus = dlcidsttResponse.getCiticStatus();
        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;

        List<TransactionStatusQueryResponse> transactionStatusQueryResponses = null;
        if (citicStatus.equals(CiticStatus.AAAAAAA)) {
            communicationStatus = CommunicationStatus.SUCCESS;

            List<DlcidsttResponseUserData> userDataList = dlcidsttResponse.getUserDataList();
            transactionStatusQueryResponses = userDataList.stream().map(dlcidsttResponseUserData -> {
                TransactionStatus transactionStatus = TransactionStatus.SUCCESS;
                CiticStatus citicStatusU = dlcidsttResponseUserData.getCiticStatus();
                String statusText = citicStatus.getStatusText();

                if (citicStatusU.equals(CiticStatus.ERROR)) {
                    transactionStatus = TransactionStatus.FAILURE;
                }
                if (!citicStatusU.equals(CiticStatus.AAAAAAA) && !citicStatusU.equals(CiticStatus.ERROR)) {
                    transactionStatus = TransactionStatus.PROCESSING;
                }

                return TransactionStatusQueryResponse.builder()
                        .transactionStatus(transactionStatus)
                        .transactionMessage(statusText)
                        .build();
            }).collect(Collectors.toList());
        }

        TransactionStatusQueryResponse transactionStatusQueryResponse = TransactionStatusQueryResponse.builder()
                .communicationStatus(communicationStatus)
                .transactionMessage(citicStatus.getStatusText())
                .transactionStatusQueryResponses(transactionStatusQueryResponses)
                .build();
        return transactionStatusQueryResponse;
    }

    /**
     * 现金管理系统余额查询请求
     *
     * @param request
     * @return
     */
    public DlbalqryRequest buildDlbalqryRequest(PaymentAccountRequest request) {

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();

        Map<String, Object> channelConfig = paymentChannelConfig.getChannelConfig();

        DlbalqryRequestUserData userData = DlbalqryRequestUserData.builder()
                .accountNo(request.getAccount())
                .build();
        List<DlbalqryRequestUserData> userDataList = Arrays.asList(userData);

        DlbalqryRequest dlbalqryRequest = DlbalqryRequest.builder()
                .paymentChannelConfig(paymentChannelConfig)
                .userName(String.valueOf(channelConfig.get("user_name")))
                .userDataList(userDataList)
                .build();

        return dlbalqryRequest;
    }

    /**
     * 现金管理系统余额查询应答
     *
     * @param dlbalqry
     * @return
     */
    public PaymentAccountResponse buildPaymentAccountResponse(DlbalqryResponse dlbalqry) {

        BigDecimal accountBalance = BigDecimal.ZERO;
        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;
        CiticStatus citicStatus = dlbalqry.getCiticStatus();

        if (citicStatus.equals(CiticStatus.AAAAAAA)) {

            communicationStatus = CommunicationStatus.SUCCESS;

            List<DlbalqryResponseUserData> userDataList = dlbalqry.getUserDataList();

            DlbalqryResponseUserData dlbalqryResponseUserData = userDataList.stream().findFirst().get();
            String balance = dlbalqryResponseUserData.getBalance();
            accountBalance = new BigDecimal(balance);
        }

        PaymentAccountResponse paymentAccountResponse = PaymentAccountResponse.builder()
                .communicationStatus(communicationStatus)
                .responseMessage(citicStatus.getStatusText())
                .accountBalance(accountBalance)
                .responseMessage(citicStatus.getStatusText())
                .build();

        return paymentAccountResponse;
    }

    /**
     * 现金管理系统支付转账请求
     *
     * @param request
     * @return
     */
    public DlinttrnRequest buildDlinttrnRequest(TransferAccountRequest request) {

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        Map<String, Object> channelConfig = paymentChannelConfig.getChannelConfig();

        TransactionParticipant payer = request.getPayer();  // 付款方
        TransactionParticipant payee = request.getPayee();  // 收款方


        DlinttrnRequestUserData userData = DlinttrnRequestUserData.builder()
                .clientID(request.getPlatformSerialNumber())
                .preFlg(PreFlg.ZERO).preDate("").preTime(null)
                .payType(PayType.TWO).payFlg(PayFlg.ZERO)
                .payAccountNo(payer.getAccount())
                .recAccountNo(payee.getAccount()).recAccountName(payee.getAccountName())
                .recOpenBankName("").recOpenBankCode("")
                .tranAmount(request.getPaymentAmount())
                .abstractt(request.getPostscript())
                .memo(request.getMemo())
                .chkNum("")
                .build();

        List<DlinttrnRequestUserData> userDataList = Arrays.asList(userData);

        DlinttrnRequest dlinttrnRequest = DlinttrnRequest.builder()
                .paymentChannelConfig(paymentChannelConfig)
                .userName(String.valueOf(channelConfig.get("user_name")))
                .userDataList(userDataList)
                .build();

        return dlinttrnRequest;

    }

    /**
     * 现金管理系统支付转账应答
     *
     * @param dlinttrn
     * @return
     */
    public TransferAccountResponse buildTransferAccountResponse(DlinttrnResponse dlinttrn) {

        CiticStatus citicStatus = dlinttrn.getCiticStatus();

        // 通讯状态
        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;

        // 交易状态
        TransactionStatus transactionStatus = TransactionStatus.SUCCESS;
        // 交易状态信息
        String transactionMessage = "";
        // 平台流水号
        String platformSerialNumber = null;

        if (citicStatus.equals(CiticStatus.AAAAAAA)) {

            communicationStatus = CommunicationStatus.SUCCESS;

            List<DlinttrnResponseUserData> userDataList = dlinttrn.getUserDataList();
            DlinttrnResponseUserData userData = userDataList.stream().findFirst().get();
            CiticStatus citicStatusUserData = userData.getCiticStatus();
            if (citicStatusUserData.equals(CiticStatus.ERROR)) {
                transactionStatus = TransactionStatus.FAILURE;
            }
            if (!citicStatusUserData.equals(CiticStatus.AAAAAAA) && !citicStatusUserData.equals(CiticStatus.ERROR)) {
                transactionStatus = TransactionStatus.PROCESSING;
            }
            transactionMessage = citicStatusUserData.getStatusText();
            platformSerialNumber = userData.getClientID();
        }


        TransferAccountResponse transferAccountResponse = TransferAccountResponse.builder()
                .communicationStatus(communicationStatus)
                .responseMessage(citicStatus.getStatusText())
                .platformSerialNumber(platformSerialNumber)
                .transactionStatus(transactionStatus).transactionResult(transactionMessage)
                .build();

        return transferAccountResponse;
    }

    /**
     * 现金管理系统账户明细查询请求
     * @param request
     * @return
     */
    public DltrnallRequest buildDltrnallRequest(AccountDetailsRequest request) {

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        Map<String, Object> channelConfig = paymentChannelConfig.getChannelConfig();

        DltrnallRequest dltrnallRequest = DltrnallRequest.builder()
                .paymentChannelConfig(paymentChannelConfig)
                .userName(String.valueOf(channelConfig.get("user_name")))
                .accountNo(request.getAccount())
                .lowAmount(BigDecimal.ZERO).upAmount(new BigDecimal(999999999))
                .startDate(DateUtil.getLocalDate(request.getStartDate(), DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT))
                .endDate(DateUtil.getLocalDate(request.getStartDate(), DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT))
                .pageNumber(request.getPageSize())
                .startRecord(request.getStartNum())
                .controlFlag(ControlFlag.ONE)
                .build();

        return dltrnallRequest;

    }

    /**
     * 现金管理系统账户明细查询应答
     * @param dltrnallResponse
     * @return
     */
    public AccountDetailResponse buildAccountDetailResponse(DltrnallResponse dltrnallResponse) {

        CiticStatus citicStatus = dltrnallResponse.getCiticStatus();

        // 通讯状态
        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;
        // 账户明细
        List<AccountDetail> accountDetails = null;
        if (citicStatus.equals(CiticStatus.AAAAAAA)) {

            communicationStatus = CommunicationStatus.SUCCESS;

            List<DltrnallResponseUserData> userDataList = dltrnallResponse.getUserDataList();
            accountDetails = userDataList.stream().map(dltrnallResponseUserData -> {
                String localDateTime = dltrnallResponseUserData.getTranDate() + dltrnallResponseUserData.getTranTime();
                return AccountDetail.builder()
                        .account(dltrnallResponse.getAccountNo())
                        .accountName(dltrnallResponse.getAccountName())
                        .transactionDateTime(DateUtil.getLocalDateTime(localDateTime, DateUtil.Pattern.NORM_DATETIME_CLOSE_PATTERN))
                        .oppositeAccount(dltrnallResponseUserData.getOppAccountNo()).oppositeAccountName(dltrnallResponseUserData.getOppAccountName())
                        .transactionAmount(new BigDecimal(dltrnallResponseUserData.getTranAmount()))
                        .postscript(dltrnallResponseUserData.getAbstractt())
                        .accountBalance(new BigDecimal(dltrnallResponseUserData.getBalance()))
                        .build();
            }).collect(Collectors.toList());

        }

        AccountDetailResponse accountDetailResponse = AccountDetailResponse.builder()
                .communicationStatus(communicationStatus)
                .responseMessage(citicStatus.getStatusText())
                .account(dltrnallResponse.getAccountNo())
                .accountName(dltrnallResponse.getAccountName())
                .accountDetails(accountDetails)
                .build();

        return accountDetailResponse;

    }

    /**
     * 现金管理系统电子回单查询请求
     * @param request
     * @return
     */
    public DleddrsqRequest buildDleddrsqRequest(ElecReceiptQueryRequest request) {

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        Map<String, Object> channelConfig = paymentChannelConfig.getChannelConfig();

        DleddrsqRequest dleddrsqRequest = DleddrsqRequest.builder()
                .paymentChannelConfig(paymentChannelConfig)
                .userName(String.valueOf(channelConfig.get("user_name")))
                .qryType(QryType.ONE)
                .accNo(request.getAccount())
                .billType(BillType.ZERO)
                .minAmt(BigDecimal.ZERO).maxAmt(new BigDecimal(999999999))
                .startDate(DateUtil.getLocalDate(request.getStartDate(), DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT))
                .endDate(DateUtil.getLocalDate(request.getStartDate(), DateUtil.Pattern.NORM_DATE_CLOSE_FORMAT))
                .pageSize(9999).startNo(request.getStartNum())
                .build();

        return dleddrsqRequest;
    }

    /**
     * 现金管理系统电子回单查询应答
     * @param dleddrsq
     * @return
     */
    public ElecReceiptQueryResponse buildElecReceiptQueryResponse(DleddrsqResponse dleddrsq) {

        CiticStatus citicStatus = dleddrsq.getCiticStatus();

        // 通讯状态
        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;

        List<ElecReceiptQuery> elecReceiptQueries = null;

        if (citicStatus.equals(CiticStatus.AAAAAAA)) {
            communicationStatus = CommunicationStatus.SUCCESS;
            List<DleddrsqResponseUserData> userDataList = dleddrsq.getUserDataList();
            elecReceiptQueries = userDataList.stream().map(dleddrsqResponseUserData -> {
                return ElecReceiptQuery.builder()
                        .elecReceiptNum(dleddrsqResponseUserData.getBrseqNo())
                        .elecReceiptType(dleddrsqResponseUserData.getBillType())
                        .build();
            }).collect(Collectors.toList());

        }

        ElecReceiptQueryResponse elecReceiptQueryResponse = ElecReceiptQueryResponse.builder()
                .communicationStatus(communicationStatus)
                .responseMessage(citicStatus.getStatusText())
                .totalCount(Integer.parseInt(dleddrsq.getTotalCount()))
                .elecReceiptQueries(elecReceiptQueries)
                .build();
        return elecReceiptQueryResponse;
    }

    /**
     * 下载电子回单请求
     * @param request
     * @return
     */
    public DledcdtdRequest buildDledcdtdRequest(ElecReceiptDownloadRequest request) {

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        Map<String, Object> channelConfig = paymentChannelConfig.getChannelConfig();
        List<ElecReceiptDownload> elecReceiptDownloads = request.getElecReceiptDownloads();

        List<DledcdtdRequestUserData> userDataList = elecReceiptDownloads.stream().map(elecReceiptDownload -> {
            return DledcdtdRequestUserData.builder()
                    .brSeqNo(elecReceiptDownload.getElecReceiptNum())
                    .billType(BillType.getBillType(elecReceiptDownload.getElecReceiptType()))
                    .build();
        }).collect(Collectors.toList());

        DledcdtdRequest dledcdtdRequest = DledcdtdRequest.builder()
                .paymentChannelConfig(paymentChannelConfig)
                .userName(String.valueOf(channelConfig.get("user_name")))
                .bankId("").isCurrDay(IsCurrDay.ONE)
                .accNo(request.getAccount())
                .userDataList(userDataList)
                .build();

        return dledcdtdRequest;
    }

    /**
     * 电子回单下载应答
     * @param dledcdtd
     * @return
     */
    public ElecReceiptDownloadResponse buildElecReceiptDownloadResponse(DledcdtdResponse dledcdtd) {

        CiticStatus citicStatus = dledcdtd.getCiticStatus();

        // 通讯状态
        CommunicationStatus communicationStatus = CommunicationStatus.FAIL;
        List<ElecReceiptDownload> elecReceiptDownloads = null;
        if (citicStatus.equals(CiticStatus.AAAAAAA)) {
            communicationStatus = CommunicationStatus.SUCCESS;
            List<DledcdtdResponseUserData> userDataList = dledcdtd.getUserDataList();

            elecReceiptDownloads = userDataList.stream().map(dledcdtdResponseUserData -> {
                return ElecReceiptDownload.builder()
                        .elecReceiptNum(dledcdtdResponseUserData.getBrSeqNo())
                        .elecReceiptType(dledcdtdResponseUserData.getBillType().getBillType())
                        .elecReceiptName(dledcdtdResponseUserData.getPdfName())
                        .build();
            }).collect(Collectors.toList());

        }

        ElecReceiptDownloadResponse elecReceiptDownloadResponse = ElecReceiptDownloadResponse.builder()
                .communicationStatus(communicationStatus)
                .responseMessage(citicStatus.getStatusText())
                .elecReceiptDownloads(elecReceiptDownloads)
                .fileContent(dledcdtd.getFileConTent())
                .fileName(dledcdtd.getFileName())
                .size(dledcdtd.getSize() == null || "".equals(dledcdtd.getSize()) ? 0 : Integer.parseInt(dledcdtd.getSize()))
                .build();
        return elecReceiptDownloadResponse;
    }
}
