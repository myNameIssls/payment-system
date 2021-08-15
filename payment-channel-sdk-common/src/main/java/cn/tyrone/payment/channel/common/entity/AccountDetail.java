package cn.tyrone.payment.channel.common.entity;

import cn.tyrone.payment.channel.common.enums.PayFlowDirection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 账户明细应答对象
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetail {

    /**
     * 账户（虚拟账户、共管账户、银行账户等）
     */
    private String account;

    /**
     * 户名（虚拟账户、共管账户、银行账户等）
     */
    private String accountName;

    /**
     * 交易时间
     */
    private LocalDateTime transactionDateTime;

    /**
     * 对方账号
     */
    private String oppositeAccount;

    /**
     * 对方账号名称
     */
    private String oppositeAccountName;

    /**
     * 交易金额
     */
    private BigDecimal transactionAmount;

    /**
     * 手续费
     */
    private BigDecimal feeAmount;

    /**
     * 收支方向
     */
    private PayFlowDirection payFlowDirection;

    /**
     * 附言
     */
    private String postscript;

    /**
     * 账户余额
     */
    private BigDecimal accountBalance;

    /**
     * 原平台流水号
     */
    private String originalPlatformSerialNumber;

    /**
     * 原渠道流水号
     */
    private String originalChannelSerialNumber;

}
