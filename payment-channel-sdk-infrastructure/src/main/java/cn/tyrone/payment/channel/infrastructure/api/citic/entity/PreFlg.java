package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

/**
 * 支付时效
 */
public enum PreFlg {

    ZERO("0", "非预约"),
    ONE("1", "预约"),
    TWO("2", "次日"),
    ;

    /**
     * 操作
     */
    private String preFlg;

    /**
     * 说明
     */
    private String describe;

    private PreFlg(String preFlg, String describe){
        this.preFlg = preFlg;
        this.describe = describe;
    }

    public String getPreFlg() {
        return preFlg;
    }

    public void setPreFlg(String preFlg) {
        this.preFlg = preFlg;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
