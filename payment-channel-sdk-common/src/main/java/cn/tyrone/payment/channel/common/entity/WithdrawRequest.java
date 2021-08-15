package cn.tyrone.payment.channel.common.entity;

import cn.tyrone.payment.channel.common.enums.SettlementMethod;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * 出金请求
 */
@Data
@SuperBuilder
public class WithdrawRequest extends CommonRequest {

    /**
     * 付款方账户
     */
    private String payerAccount;
    /**
     * 付款方账户名称
     */
    private String payerAccountName;
    /**
     * 收款方账户
     */
    private String payeeAccount;
    /**
     * 收款方账户名称
     */
    private String payeeAccountName;
    /**
     * 付款金额
     */
    private BigDecimal paymentAmount;

    /**
     * 收款银行支行行联号
     */
    private String payeeBankNumber;

    /**
     * 结算方式
     */
    private SettlementMethod settlementMethod;

    private List<WithdrawRequest> withdrawRequests;

}
