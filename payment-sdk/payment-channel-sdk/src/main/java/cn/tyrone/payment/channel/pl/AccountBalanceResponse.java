package cn.tyrone.payment.channel.pl;

import cn.tyrone.payment.sdk.extend.ToStringStyleExtend;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * 账户余额应答
 */
@Data
@SuperBuilder
@ToString(callSuper = true)
public class AccountBalanceResponse extends AbstractCommonResponse {

    /**
     * 账户
     */
    private String subAccount;

    /**
     * 账户名称
     */
    private String subAccountName;

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

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyleExtend.JSON_STYLE_EXTEND);

    }

}
