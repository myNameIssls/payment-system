package cn.tyrone.payment.channel.common.entity;

import cn.tyrone.payment.channel.common.enums.TransactionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 转账应答
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class TransferAccountResponse extends CommonResponse {

    /**
     * 业务单号
     */
    private String businessOrderNo;

    /**
     * 交易状态
     */
    private TransactionStatus transactionStatus;

    /**
     * 交易结果信息
     */
    private String transactionResult;

}
