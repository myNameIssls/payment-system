package cn.tyrone.payment.account.domain.virtual.valueobject;

/**
 * 虚拟账户分类
 */
public enum VirtualAccountType {

    VIRTUAL_ACCOUNT("VIRTUAL_ACCOUNT", "虚拟账户"),
    INCOME_ACCOUNT("INCOME_ACCOUNT", "收益账户"),
    ;

    public String virtualAccountType;

    public String describe;

    private VirtualAccountType(String virtualAccountType, String describe) {
        this.virtualAccountType = virtualAccountType;
        this.describe = describe;
    }
}
