package cn.tyrone.payment.channelctx.acl.adapter.route.citic.model;

/**
 * 状态标志  char(1) 0 成功 1 失败 2未知 3审核拒绝 4 用户撤销
 */
public enum Stt {

    ZERO("0", "成功"),
    ONE("1", "失败"),
    TWO("2", "未知"),
    THREE("3", "审核拒绝"),
    FOUR("4", "用户撤销"),
    ;

    /**
     * 操作
     */
    private String stt;

    /**
     * 说明
     */
    private String describe;

    private Stt(String stt, String describe){
        this.stt = stt;
        this.describe = describe;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
