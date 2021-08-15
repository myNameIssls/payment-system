package cn.tyrone.payment.channel.domain.adapter;

import cn.tyrone.payment.channel.common.entity.*;

/**
 * 支付公共接口
 */
public interface ICommunalPaymentServiceAdapter {

    /**
     * 余额查询
     * @param request
     * @return
     */
    PaymentAccountResponse balanceQuery(PaymentAccountRequest request);

    /**
     * 转账
     * @param request
     * @return
     */
    TransferAccountResponse transferAccount(TransferAccountRequest request);

    /**
     * 批量转账
     * @param request
     * @return
     */
    BatchTransferAccountResponse batchTransferAccount(BatchTransferAccountRequest request);

    /**
     * 交易结果查询
     * @param request
     * @return
     */
    TransactionStatusQueryResponse transactionStatusQuery(TransactionStatusQueryRequest request);


}
