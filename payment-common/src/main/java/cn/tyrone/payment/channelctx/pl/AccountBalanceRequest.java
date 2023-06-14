package cn.tyrone.payment.channelctx.pl;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 账户余额对象
 */
@Data
@SuperBuilder
@ToString(callSuper = true)
public class AccountBalanceRequest extends AbstractCommonRequest {

    /**
     * 账户
     */
    private String subAccount;

    /**
     * 账户名称
     */
    private String subAccountName;

}
