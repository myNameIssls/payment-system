package cn.tyrone.payment.channel.common.entity;

import cn.tyrone.payment.channel.common.enums.TransactionStatus;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 交易状态查询应答
 */
@Data
@SuperBuilder
@ToString(callSuper = true)
public class TransactionStatusQueryResponse extends CommonResponse {

    /**
     * 交易状态
     */
    private TransactionStatus transactionStatus;

    /**
     * 交易信息
     */
    private String transactionMessage;

    private List<TransactionStatusQueryResponse> transactionStatusQueryResponses;

}
