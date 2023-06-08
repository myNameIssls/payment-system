package cn.tyrone.payment.channelctx.pl;

import cn.tyrone.payment.commonctx.pl.SecondaryAccountType;

public class OpenAccountRequest {

    /**
     * 客户编码
     */
    private String customerCode;

    /**
     * 账户
     */
    private String subAccount;

    /**
     * 账户名称
     */
    private String subAccountName;

    /**
     * 功能标示
     */
    private FunctionFlag functionFlag;

    /**
     * 二级账户类型
     */
    private SecondaryAccountType secondaryAccountType;

    /**
     * 客户性质
     */
    private CustomerProperty customerProperty;

    /**
     * 企业法人
     */
    private LegalPerson legalPerson;

    /**
     * 银行编码
     */
    private String bankCode;
    /**
     * 银行账号
     */
    private String bankAccount;
    /**
     * 银行支行大额行号
     */
    private String bankBranchCode;


}
