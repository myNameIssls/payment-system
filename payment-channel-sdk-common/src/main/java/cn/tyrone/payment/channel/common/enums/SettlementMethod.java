package cn.tyrone.payment.channel.common.enums;

/**
 * 结算方式
 */
public enum SettlementMethod {

    T0("T0", "T0结算"),
    T1("T1", "T1结算"),

    ;

    private String settlementMethod;

    private String describe;

    SettlementMethod(String settlementMethod, String describe) {
        this.settlementMethod = settlementMethod;
        this.describe = describe;
    }

    public String getSettlementMethod() {
        return settlementMethod;
    }

    public String getDescribe() {
        return describe;
    }

}

