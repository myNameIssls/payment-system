package cn.tyrone.payment.outreach.channel.citic.enums;

/**
 * 自动分配利息标示
 */
public enum FeeType {

    ZERO("0", "不收取"),
    ONE("1", "实时收取"),
    TWO("2", "月末累计")
    ;

    /**
     * 操作
     */
    private String feeType;

    /**
     * 说明
     */
    private String describe;

    private FeeType(String feeType, String describe){
        this.feeType = feeType;
        this.describe = describe;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
