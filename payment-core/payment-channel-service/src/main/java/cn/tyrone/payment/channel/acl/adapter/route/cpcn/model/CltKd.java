package cn.tyrone.payment.channel.acl.adapter.route.cpcn.model;

/**
 * 客户性质
 */
public enum CltKd {
    ZERO("0", "个人"),
    ONE("1", "公司"),
    ;
    public String cltKd;

    public String describe;

    CltKd(String cltKd, String describe) {
        this.cltKd = cltKd;
        this.describe = describe;
    }
}
