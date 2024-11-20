package cn.tyrone.payment.outreach.channel.citic.enums;

/**
 * 转账类型varchar(2) ，BF：转账；BG：解冻；BH：解冻支付；BR：冻结；BS：支付冻结
 */
public enum TranType {

    BF("BF", "转账"),
    BG("BG", "解冻"),
    BH("BH", "解冻支付"),
    BR("BR", "冻结"),
    BS("BS", "支付冻结"),
    ;

    /**
     * 操作
     */
    private String tranType;

    /**
     * 说明
     */
    private String describe;

    private TranType(String tranType, String describe){
        this.tranType = tranType;
        this.describe = describe;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
