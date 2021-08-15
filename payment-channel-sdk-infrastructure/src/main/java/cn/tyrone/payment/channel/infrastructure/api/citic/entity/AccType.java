package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

/**
 * 资金分簿类型
 */
public enum AccType {

    COMMON("03", "一般交易账号"),
    BOND("04", "保证金账号"),
    BID_BOND("11", "招投标保证金"),
    ;

    /**
     * 操作
     */
    private String accType;

    /**
     * 说明
     */
    private String describe;

    private AccType(String accType, String describe){
        this.accType = accType;
        this.describe = describe;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
