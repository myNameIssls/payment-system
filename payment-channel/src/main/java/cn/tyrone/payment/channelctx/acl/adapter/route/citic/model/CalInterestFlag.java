package cn.tyrone.payment.channelctx.acl.adapter.route.citic.model;

/**
 * 是否计算利息标志
 */
public enum CalInterestFlag {

    ZERO("0", "不计息"),
    ONE("1", "不分段计息"),
    TWO("2", "分段计息"),
    ;

    /**
     * 操作
     */
    private String calInterestFlag;

    /**
     * 说明
     */
    private String describe;

    private CalInterestFlag(String calInterestFlag, String describe){
        this.calInterestFlag = calInterestFlag;
        this.describe = describe;
    }

    public String getCalInterestFlag() {
        return calInterestFlag;
    }

    public void setCalInterestFlag(String calInterestFlag) {
        this.calInterestFlag = calInterestFlag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
