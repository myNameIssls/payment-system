package cn.tyrone.payment.channelctx.acl.adapter.route.citic.model;

/**
 * 中信银行操作类型
 */
public enum AppFlag {

    APP_SYS("1", "应用系统"),
    B2B("2", "B2B电子商务"),
    BID_SECURITY("3", "投标保证金"),
    ;

    /**
     * 操作
     */
    private String appFlag;

    /**
     * 说明
     */
    private String describe;

    private AppFlag(String appFlag, String describe){
        this.appFlag = appFlag;
        this.describe = describe;
    }

    public String getAppFlag() {
        return appFlag;
    }

    public void setAppFlag(String appFlag) {
        this.appFlag = appFlag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
