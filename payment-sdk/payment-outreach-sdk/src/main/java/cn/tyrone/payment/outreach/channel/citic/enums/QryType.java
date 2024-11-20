package cn.tyrone.payment.outreach.channel.citic.enums;

/**
 * 支付时效
 */
public enum QryType {

    ONE("1", "T0"),
    TWO("2", "T1"),
    ;

    /**
     * 操作
     */
    private String qryType;

    /**
     * 说明
     */
    private String describe;

    private QryType(String qryType, String describe){
        this.qryType = qryType;
        this.describe = describe;
    }

    public String getQryType() {
        return qryType;
    }

    public void setQryType(String qryType) {
        this.qryType = qryType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
