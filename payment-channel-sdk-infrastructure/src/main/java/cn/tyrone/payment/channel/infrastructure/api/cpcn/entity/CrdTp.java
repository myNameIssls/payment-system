package cn.tyrone.payment.channel.infrastructure.api.cpcn.entity;

/**
 * 银行账户卡类型
 */
public enum CrdTp {

    ONE("1", "个人借记卡（储蓄卡），默认"),
    A("A", "企业一般结算账户，默认"),
    ;

    public String crdTp;

    public String describe;

    private CrdTp(String crdTp, String describe){
        this.crdTp = crdTp;
        this.describe = describe;
    }

}
