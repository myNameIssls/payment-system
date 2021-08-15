package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 开户应答
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class OpenAccountResponse extends CommonResponse {

    /**
     * 客户号
     */
    private String customerNo;

    /**
     * 虚拟账户
     */
    private String virtualAccount;

    /**
     * 虚拟账户开户名称
     */
    private String virtualAccountName;

    /**
     * 银行电子账号
     */
    private String bankElecAccount;

}
