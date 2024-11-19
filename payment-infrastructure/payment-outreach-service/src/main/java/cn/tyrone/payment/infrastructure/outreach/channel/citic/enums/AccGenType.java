package cn.tyrone.payment.infrastructure.outreach.channel.citic.enums;

/**
 * 中信银行操作类型
 */
public enum AccGenType {

    AUTO_INPUT("0", "自动输入"),
    MANUAL_GENERATION("1", "手动生成")
    ;

    /**
     * 操作
     */
    private String accGenType;

    /**
     * 说明
     */
    private String describe;

    private AccGenType(String accGenType, String describe){
        this.accGenType = accGenType;
        this.describe = describe;
    }

    public String getAccGenType() {
        return accGenType;
    }

    public void setAccGenType(String accGenType) {
        this.accGenType = accGenType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
