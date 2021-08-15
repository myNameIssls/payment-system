package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 账户查询请求对象
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class PaymentAccountRequest extends CommonRequest {

    /**
     * 账户
     */
    private String account;

    /**
     * 账户开户名称
     */
    private String accountName;

}
