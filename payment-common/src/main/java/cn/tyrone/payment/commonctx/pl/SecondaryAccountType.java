package cn.tyrone.payment.commonctx.pl;

/**
 * 二级账户类型
 */
public enum SecondaryAccountType {

    ONE("1", "客户资金账户"),
    TWO("2", "合作方备付金账户"),
    THREE("3", "合作方收益账户"),
    ;

    public String secondaryAccountType;

    public String describe;

    private SecondaryAccountType(String accTp, String describe){
        this.secondaryAccountType = accTp;
        this.describe = describe;
    }

}
