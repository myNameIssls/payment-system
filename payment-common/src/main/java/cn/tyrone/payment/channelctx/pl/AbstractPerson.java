package cn.tyrone.payment.channelctx.pl;

import cn.tyrone.payment.commonctx.pl.CertificateType;

/**
 *
 */
public abstract class AbstractPerson {

    /**
     * 客户名称
     */
    private String name;

    /**
     * 证件类型
     */
    private CertificateType certificateType;

    /**
     * 客户证件号码
     */
    private String certificateNum;

    /**
     * 证件正面存储地址
     */
    private String certificateFrontStorageAddr;

    /**
     * 证件正面存储地址
     */
    private String certificateBackStorageAddr;

    /**
     * 证件签发起始日
     */
    private String certificateSignDateBegin;

    /**
     * 证件签发到期日
     */
    private String certificateSignDateEnd;

    /**
     * 手机号码
     */
    private String phoneNumber;

}
