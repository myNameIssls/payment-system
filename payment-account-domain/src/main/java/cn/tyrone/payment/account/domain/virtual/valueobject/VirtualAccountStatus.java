package cn.tyrone.payment.account.domain.virtual.valueobject;

/**
 * 虚拟账户分类
 */
public enum VirtualAccountStatus {

    VIRTUAL_ACCOUNT("VIRTUAL_ACCOUNT", "虚拟账户"),
    INCOME_ACCOUNT("INCOME_ACCOUNT", "收益账户"),
    ;

    public String virtualAccountStatus;

    public String describe;

    private VirtualAccountStatus(String virtualAccountStatus, String describe) {
        this.virtualAccountStatus = virtualAccountStatus;
        this.describe = describe;
    }
}
