package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

/**
 * 支付时效
 */
public enum PreTime {

    ZERO("100000", "上午10点"),
    ONE("120000", "上午12点"),
    TWO("140000", "14点"),
    THREE("160000", "16点"),
    ;

    /**
     * 操作
     */
    private String preTime;

    /**
     * 说明
     */
    private String describe;

    private PreTime(String preTime, String describe) {
        this.preTime = preTime;
        this.describe = describe;
    }

    public String getPreTime() {
        return preTime;
    }

    public void setPreTime(String preTime) {
        this.preTime = preTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
