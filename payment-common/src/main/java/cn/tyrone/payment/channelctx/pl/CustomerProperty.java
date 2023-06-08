package cn.tyrone.payment.channelctx.pl;

/**
 * 客户性质
 */
public enum CustomerProperty {

    /**
     * 企业
     */
    ENTERPRISE("ENTERPRISE", "企业"),

    /**
     * 个人
     */
    PERSONAL("PERSONAL", "个人"),

    ;

    private String customerPropertity;

    private String describe;

    private CustomerProperty(String customerPropertity, String describe){
        this.customerPropertity = customerPropertity;
        this.describe = describe;
    }

}
