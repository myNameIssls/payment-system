package cn.tyrone.payment.channel.common.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 客户公共信息
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractBaseCustomer {

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String postNum;

    /**
     * 手机号码
     */
    private String phoneNumber;

}
