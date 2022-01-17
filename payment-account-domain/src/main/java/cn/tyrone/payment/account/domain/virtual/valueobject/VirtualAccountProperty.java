package cn.tyrone.payment.account.domain.virtual.valueobject;

/**
 * 虚拟账户性质
 */
public enum VirtualAccountProperty {

    ENTERPRISE("ENTERPRISE", "企业账户"),
    PERSONAL("PERSONAL", "个人账户"),
    ;

    public String virtualAccountProperty;

    public String describe;

    private VirtualAccountProperty(String virtualAccountProperty, String describe) {
        this.virtualAccountProperty = virtualAccountProperty;
        this.describe = describe;
    }
}
