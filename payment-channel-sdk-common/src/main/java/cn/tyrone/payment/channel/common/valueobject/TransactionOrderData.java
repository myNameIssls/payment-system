package cn.tyrone.payment.channel.common.valueobject;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 支付订单对象
 */
@Data
@Builder
public class TransactionOrderData implements Serializable {

    /**
     * 付款方信息
     */
    private TransactionParticipant payer;

    /**
     * 收款方信息
     */
    private TransactionParticipant payee;

    /**
     * 交易业务信息
     */
    private TransactionBusiness transactionBusiness;

    /**
     * 扩展字段
     */
    private Map<String, Object> extendParams;

}
