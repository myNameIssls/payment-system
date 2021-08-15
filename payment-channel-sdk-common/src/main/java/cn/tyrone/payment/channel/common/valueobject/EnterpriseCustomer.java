package cn.tyrone.payment.channel.common.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 企业信息数据传输对象
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseCustomer extends AbstractBaseCustomer implements Serializable {

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 统一社会信用代码
     */
    private String unifiedSocialCreditCode;

    /**
     * 企业法人
     */
    private EnterpriseCorporation enterpriseCorporation;

}
