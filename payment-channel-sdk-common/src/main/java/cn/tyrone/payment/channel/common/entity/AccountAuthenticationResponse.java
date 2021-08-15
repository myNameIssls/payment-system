package cn.tyrone.payment.channel.common.entity;

import cn.tyrone.payment.channel.common.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 企业账户认证应答
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class AccountAuthenticationResponse extends CommonResponse {

    /**
     * 交易状态
     */
    private TransactionStatus transactionStatus;

    /**
     * 交易信息
     */
    private String transactionMessage;

    /**
     * 当前可验证次数
     */
    private int availableVeriCount;



}
