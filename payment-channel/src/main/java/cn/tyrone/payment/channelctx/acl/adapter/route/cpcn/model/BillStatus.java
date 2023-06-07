package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.model;

/**
 * 支付状态
 */
public enum BillStatus {

    ONT("1", "成功"),
    TWO("2", "失败"),

    ;

    private String billStatus;

    private String describe;

    BillStatus(String billStatus, String describe) {
        this.billStatus = billStatus;
        this.describe = describe;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
