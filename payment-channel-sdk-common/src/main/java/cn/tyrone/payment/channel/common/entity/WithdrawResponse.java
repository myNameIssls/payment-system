package cn.tyrone.payment.channel.common.entity;

import cn.tyrone.payment.channel.common.enums.TransactionStatus;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@ToString(callSuper = true)
public class WithdrawResponse extends CommonResponse {

    /**
     * 交易状态
     */
    private TransactionStatus transactionStatus;

    /**
     * 交易信息
     */
    private String transactionMessage;

    private List<WithdrawResponse> withdrawResponses;

}
