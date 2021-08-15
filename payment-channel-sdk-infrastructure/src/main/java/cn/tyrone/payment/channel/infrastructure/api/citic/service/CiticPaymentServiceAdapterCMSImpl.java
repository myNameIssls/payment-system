package cn.tyrone.payment.channel.infrastructure.api.citic.service;


import cn.tyrone.payment.channel.common.entity.*;
import cn.tyrone.payment.channel.domain.adapter.ICiticPaymentServiceAdapterCMS;
import cn.tyrone.payment.channel.infrastructure.api.citic.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CiticPaymentServiceAdapterCMSImpl implements ICiticPaymentServiceAdapterCMS {

    @Autowired
    private CiticMessageService messageService;
    @Autowired
    private CiticPaymentService paymentService;

    @Override
    public PaymentAccountResponse balanceQuery(PaymentAccountRequest request) {

        DlbalqryRequest dlbalqryRequest = messageService.buildDlbalqryRequest(request);
        DlbalqryResponse dlbalqry = paymentService.dlbalqry(dlbalqryRequest);
        PaymentAccountResponse paymentAccountResponse = messageService.buildPaymentAccountResponse(dlbalqry);
        return paymentAccountResponse;
    }

    @Override
    public TransferAccountResponse transferAccount(TransferAccountRequest request) {

        DlinttrnRequest dlinttrnRequest = messageService.buildDlinttrnRequest(request);
        DlinttrnResponse dlinttrn = paymentService.dlinttrn(dlinttrnRequest);
        TransferAccountResponse transferAccountResponse = messageService.buildTransferAccountResponse(dlinttrn);

        return transferAccountResponse;
    }

    @Override
    public BatchTransferAccountResponse batchTransferAccount(BatchTransferAccountRequest request) {
        return null;
    }

    /**
     * 交易状态查询
     * @param request
     * @return
     */
    @Override
    public TransactionStatusQueryResponse transactionStatusQuery(TransactionStatusQueryRequest request) {
        DlcidsttRequest dlcidsttRequest = messageService.buildDlcidsttRequest(request);
        DlcidsttResponse dlcidstt = paymentService.dlcidstt(dlcidsttRequest);
        TransactionStatusQueryResponse transactionStatusQueryResponse = messageService.buildTransactionStatusQueryResponse(dlcidstt);
        return transactionStatusQueryResponse;
    }

    /**
     * 账户明细
     * @param request
     * @return
     */
    @Override
    public AccountDetailResponse accountDetail(AccountDetailsRequest request) {

        DltrnallRequest dltrnallRequest = messageService.buildDltrnallRequest(request);
        DltrnallResponse dltrnallResponse = paymentService.dltrnall(dltrnallRequest);
        AccountDetailResponse accountDetailResponse = messageService.buildAccountDetailResponse(dltrnallResponse);

        return accountDetailResponse;
    }

    /**
     * 电子回单查询
     * @param receiptQueryRequest
     * @return
     */
    @Override
    public ElecReceiptQueryResponse elecReceiptQuery(ElecReceiptQueryRequest receiptQueryRequest) {

        DleddrsqRequest dleddrsqRequest = messageService.buildDleddrsqRequest(receiptQueryRequest);
        DleddrsqResponse dleddrsq = paymentService.dleddrsq(dleddrsqRequest);
        ElecReceiptQueryResponse elecReceiptQueryResponse = messageService.buildElecReceiptQueryResponse(dleddrsq);
        return elecReceiptQueryResponse;
    }

    /**
     * 电子回单下载
     * @param request
     * @return
     */
    @Override
    public ElecReceiptDownloadResponse elecReceiptDownload(ElecReceiptDownloadRequest request) {

        DledcdtdRequest dledcdtdRequest = messageService.buildDledcdtdRequest(request);
        DledcdtdResponse dledcdtd = paymentService.dledcdtd(dledcdtdRequest);
        ElecReceiptDownloadResponse elecReceiptDownloadResponse = messageService.buildElecReceiptDownloadResponse(dledcdtd);

        return elecReceiptDownloadResponse;
    }
}
