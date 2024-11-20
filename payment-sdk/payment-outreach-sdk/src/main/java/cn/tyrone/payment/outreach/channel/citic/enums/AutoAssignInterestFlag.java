package cn.tyrone.payment.outreach.channel.citic.enums;

/**
 * 自动分配利息标示
 */
public enum AutoAssignInterestFlag {

    ZERO("0", "否"),
    ONE("1", "是"),
    ;

    /**
     * 操作
     */
    private String autoAssignInterestFlag;

    /**
     * 说明
     */
    private String describe;

    private AutoAssignInterestFlag(String autoAssignInterestFlag, String describe){
        this.autoAssignInterestFlag = autoAssignInterestFlag;
        this.describe = describe;
    }

    public String getAutoAssignInterestFlag() {
        return autoAssignInterestFlag;
    }

    public void setAutoAssignInterestFlag(String autoAssignInterestFlag) {
        this.autoAssignInterestFlag = autoAssignInterestFlag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
