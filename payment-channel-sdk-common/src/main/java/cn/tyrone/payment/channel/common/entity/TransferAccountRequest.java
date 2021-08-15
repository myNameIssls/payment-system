package cn.tyrone.payment.channel.common.entity;

import cn.tyrone.payment.channel.common.valueobject.TransactionParticipant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 转账
 * A账户到B账户的行为
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TransferAccountRequest extends CommonRequest {

    /**
     * 付款方信息
     */
    private TransactionParticipant payer;

    /**
     * 收款方信息
     */
    private TransactionParticipant payee;

    /**
     * 付款金额
     */
    private BigDecimal paymentAmount;

    /**
     * 业务单号
     */
    private String businessOrderNo;

    /**
     * 支付单号
     */
    private String paymentOrderNo;

    /**
     * 附言
     */
    private String postscript;

    /**
     * 备注
     */
    private String memo;

}
