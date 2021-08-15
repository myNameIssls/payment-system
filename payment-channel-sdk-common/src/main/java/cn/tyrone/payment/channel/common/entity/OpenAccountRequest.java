package cn.tyrone.payment.channel.common.entity;


import cn.tyrone.payment.channel.common.enums.AccountBusinessType;
import cn.tyrone.payment.channel.common.enums.CustomerProperty;
import cn.tyrone.payment.channel.common.valueobject.Contact;
import cn.tyrone.payment.channel.common.valueobject.EnterpriseCustomer;
import cn.tyrone.payment.channel.common.valueobject.PersonCustomer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 开户请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OpenAccountRequest extends CommonRequest {

    /**
     * 开户名称
     */
    private String accountName;

    /**
     * 客户号
     */
    private String customerNo;

    /**
     * 账户
     */
    private String account;

    /**
     * 客户性质
     */
    private CustomerProperty customerProperty;

    /**
     * 业务类型
     */
    private AccountBusinessType accountBusinessType;

    /**
     * 个人客户
     */
    private PersonCustomer personCustomer;

    /**
     * 企业客户信息
     */
    private EnterpriseCustomer enterpriseCustomer;

    /**
     * 联系人信息
     */
    private List<Contact> contactInfos;


    public void init() {

        if (customerProperty == null) {
            throw new NullPointerException("客户性质不允许为空");
        }

        if (customerProperty.equals(CustomerProperty.ENTERPRISE)) {
            this.accountName = this.enterpriseCustomer.getEnterpriseName();
        }

        if (customerProperty.equals(CustomerProperty.PERSONAL)) {
            this.accountName = this.personCustomer.getName();
        }

    }


}
