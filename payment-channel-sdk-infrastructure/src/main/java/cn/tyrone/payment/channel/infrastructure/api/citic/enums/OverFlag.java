package cn.tyrone.payment.channel.infrastructure.api.citic.enums;

/**
 * 是否允许透支
 */
public enum OverFlag {

    ZERO("0", "不允许"),
    ONE("1", "限额透支"),
    TWO("2", "全额透支"),
    ;

    /**
     * 操作
     */
    private String overFlag;

    /**
     * 说明
     */
    private String describe;

    private OverFlag(String overFlag, String describe){
        this.overFlag = overFlag;
        this.describe = describe;
    }

    public String getOverFlag() {
        return overFlag;
    }

    public void setOverFlag(String overFlag) {
        this.overFlag = overFlag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
