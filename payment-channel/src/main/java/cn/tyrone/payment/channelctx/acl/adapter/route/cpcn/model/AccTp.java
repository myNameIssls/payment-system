package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.model;

/**
 * 账户类型
 */
public enum AccTp {

    ONE("1", "客户资金账户"),
    TWO("2", "合作方备付金账户"),
    THREE("3", "合作方收益账户"),
    ;

    public String accTp;

    public String describe;

    private AccTp(String accTp, String describe){
        this.accTp = accTp;
        this.describe = describe;
    }

}
