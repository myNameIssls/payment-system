package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 账户信息应答
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class PaymentAccountResponse extends CommonResponse {

    /**
     * 虚拟账户
     */
    private String virtualAccount;

    /**
     * 虚拟账户名称
     */
    private String virtualAccountName;

    /**
     * 账户余额
     */
    private BigDecimal accountBalance;

    /**
     * 冻结金额
     */
    private BigDecimal frozenAmount;

    /**
     * 可用金额
     */
    private BigDecimal usableAmount;

    /**
     * t0额度
     */
    private BigDecimal t0Quota;

    /**
     * t0额度
     */
    private BigDecimal t1Quota;

}
