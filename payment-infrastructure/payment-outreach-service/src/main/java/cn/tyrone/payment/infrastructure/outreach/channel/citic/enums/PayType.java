package cn.tyrone.payment.infrastructure.outreach.channel.citic.enums;

/**
 * 支付时效
 */
public enum PayType {

    ONE("1", "跨行转账"),
    TWO("2", "行内"),
    THREE("3", "企业内部转账"),
    ;

    /**
     * 操作
     */
    private String payType;

    /**
     * 说明
     */
    private String describe;

    private PayType(String payType, String describe){
        this.payType = payType;
        this.describe = describe;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
