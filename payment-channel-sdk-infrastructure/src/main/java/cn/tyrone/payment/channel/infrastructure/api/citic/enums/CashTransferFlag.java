package cn.tyrone.payment.channel.infrastructure.api.citic.enums;

/**
 * 现转标识 0：现金；1：转帐
 */
public enum CashTransferFlag {

    ZERO("0", "现金"),
    ONE("1", "转帐"),
    ;

    /**
     * 操作
     */
    private String cashTransferFlag;

    /**
     * 说明
     */
    private String describe;

    private CashTransferFlag(String cashTransferFlag, String describe){
        this.cashTransferFlag = cashTransferFlag;
        this.describe = describe;
    }

    public String getCashTransferFlag() {
        return cashTransferFlag;
    }

    public void setCashTransferFlag(String cashTransferFlag) {
        this.cashTransferFlag = cashTransferFlag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
