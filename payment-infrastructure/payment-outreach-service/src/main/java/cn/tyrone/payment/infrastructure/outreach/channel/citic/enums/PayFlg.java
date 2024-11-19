package cn.tyrone.payment.infrastructure.outreach.channel.citic.enums;

/**
 * 支付时效
 */
public enum PayFlg {

    ZERO("0", "加急"),
    ONE("1", "普通"),
    ;

    /**
     * 操作
     */
    private String payFlg;

    /**
     * 说明
     */
    private String describe;

    private PayFlg(String payFlg, String describe){
        this.payFlg = payFlg;
        this.describe = describe;
    }

    public String getPayFlg() {
        return payFlg;
    }

    public void setPayFlg(String payFlg) {
        this.payFlg = payFlg;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
