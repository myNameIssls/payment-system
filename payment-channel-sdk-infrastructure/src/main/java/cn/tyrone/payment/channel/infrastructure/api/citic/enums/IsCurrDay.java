package cn.tyrone.payment.channel.infrastructure.api.citic.enums;

/**
 * 是否为T+0 日varchar (1)， 1：是；2：否
 */
public enum IsCurrDay {

    ONE("1", "是"),
    TWO("2", "否"),

    ;

    /**
     * 操作
     */
    private String isCurrDay;

    /**
     * 说明
     */
    private String describe;

    private IsCurrDay(String isCurrDay, String describe){
        this.isCurrDay = isCurrDay;
        this.describe = describe;
    }

    public String getIsCurrDay() {
        return isCurrDay;
    }

    public void setIsCurrDay(String isCurrDay) {
        this.isCurrDay = isCurrDay;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
