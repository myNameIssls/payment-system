package cn.tyrone.payment.account.domain.virtual.entity;

import cn.tyrone.payment.account.domain.virtual.valueobject.VirtualAccountStatus;
import cn.tyrone.payment.account.domain.virtual.valueobject.VirtualAccountType;

/**
 * 虚拟账户
 */
public class VirtualAccount {

    private Long id;

    /**
     * 虚拟账户
     */
    private String account;

    /**
     * 虚拟账户名称
     */
    private String accountName;

    /**
     * 银行电子账户
     */
    private String bankElecAccount;

    /**
     * 客户ID
     */
    private String customerId;

    /**
     * 渠道客户ID
     */
    private String channelCustomerId;

    /**
     * 支付渠道ID
     */
    private String paymentChannelId;

    /**
     * 账户类型
     */
    private VirtualAccountType virtualAccountType;

    /**
     * 账户状态
     */
    private VirtualAccountStatus virtualAccountStatus;

}
