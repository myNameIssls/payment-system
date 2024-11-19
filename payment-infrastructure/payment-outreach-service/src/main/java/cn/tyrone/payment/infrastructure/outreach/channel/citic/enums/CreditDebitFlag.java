package cn.tyrone.payment.infrastructure.outreach.channel.citic.enums;

/**
 * 借贷标识
 * 借：D，
 * 贷：C
 */
public enum CreditDebitFlag {

    D("D", "借"),
    C("C", "贷"),
    ;

    /**
     * 操作
     */
    private String creditDebitFlag;

    /**
     * 说明
     */
    private String describe;

    private CreditDebitFlag(String creditDebitFlag, String describe){
        this.creditDebitFlag = creditDebitFlag;
        this.describe = describe;
    }

    public String getCreditDebitFlag() {
        return creditDebitFlag;
    }

    public void setCreditDebitFlag(String creditDebitFlag) {
        this.creditDebitFlag = creditDebitFlag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
