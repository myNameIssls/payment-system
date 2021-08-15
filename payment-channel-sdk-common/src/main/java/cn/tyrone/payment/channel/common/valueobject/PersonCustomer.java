package cn.tyrone.payment.channel.common.valueobject;

import cn.tyrone.payment.channel.common.enums.CertificateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 个人客户
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PersonCustomer extends AbstractBaseCustomer implements Serializable {

    /**
     * 名称
     */
    private String name;

    /**
     * 证件类型
     */
    private CertificateType certificateType;

    /**
     * 证件号码
     */
    private String certificateNum;



}
