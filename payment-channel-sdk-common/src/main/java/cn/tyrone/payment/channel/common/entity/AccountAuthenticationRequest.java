package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 企业账户认证请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountAuthenticationRequest extends CommonRequest {

    /**
     * 银行编码
     */
    private String bankCode;

    /**
     * 开户名称
     */
    private String bankAccountName;

    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 打款金额
     */
    private BigDecimal amount;

}
