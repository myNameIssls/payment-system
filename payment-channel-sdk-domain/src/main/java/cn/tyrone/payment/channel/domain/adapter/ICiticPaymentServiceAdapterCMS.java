package cn.tyrone.payment.channel.domain.adapter;

import cn.tyrone.payment.channel.common.entity.*;

/**
 * 中信银行支付服务适配器
 */
public interface ICiticPaymentServiceAdapterCMS extends ICommunalPaymentServiceAdapter {

    /**
     * 账户明细
     * @param request
     * @return
     */
    public AccountDetailResponse accountDetail(AccountDetailsRequest request);

    /**
     * 电子回单查询
     * @param receiptQueryRequest
     * @return
     */
    public ElecReceiptQueryResponse elecReceiptQuery(ElecReceiptQueryRequest receiptQueryRequest);

    /**
     * 电子回单下载
     * @param request
     * @return
     */
    public ElecReceiptDownloadResponse elecReceiptDownload(ElecReceiptDownloadRequest request);


}
