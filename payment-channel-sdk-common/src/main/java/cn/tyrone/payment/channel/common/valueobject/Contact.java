package cn.tyrone.payment.channel.common.valueobject;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 联系人信息
 */
@Data
@Builder
public class Contact implements Serializable {

    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 邮件地址
     */
    private String mailAddress;

}
