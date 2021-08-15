package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

/**
 * 客户证件类型
 */
public enum TranFlag {

    ZERO("0", "异步交易"),
    ONE("1", "同步交易"),
    ;

    /**
     * 操作
     */
    private String tranFlag;

    /**
     * 说明
     */
    private String describe;

    private TranFlag(String tranFlag, String describe){
        this.tranFlag = tranFlag;
        this.describe = describe;
    }

    public String getTranFlag() {
        return tranFlag;
    }

    public void setTranFlag(String tranFlag) {
        this.tranFlag = tranFlag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
