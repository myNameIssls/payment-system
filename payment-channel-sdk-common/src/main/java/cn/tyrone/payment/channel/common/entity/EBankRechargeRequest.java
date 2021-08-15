package cn.tyrone.payment.channel.common.entity;


import cn.tyrone.payment.channel.common.enums.AccountPropertyEnum;
import cn.tyrone.payment.channel.common.enums.RechargeMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 网银充值请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EBankRechargeRequest extends CommonRequest {

    /**
     * 充值账户
     */
    private String account;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 充值金额
     */
    private BigDecimal amount;

    /**
     * 账户属性
     */
    private AccountPropertyEnum accountPropertyEnum;

    /**
     * 银行 ID
     * 示例：102:工商银行
     */
    private String bankId;

    private RechargeMethod rechargeMethod;


}
