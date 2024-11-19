package cn.tyrone.payment.infrastructure.outreach.channel.citic.enums;

/**
 * 资金分簿凭证打印更换 char(1)，0：全部显示；1：显示资金分簿名和账号；2：显示实体账户名和账号；
 * 3：显示资金分簿名和实体账号；4：显示实体账户名和资金分簿编号；
 * appFlag为3时，必须为0
 */
public enum SubAccPrintParm {

    ZERO("0", "全部显示"),
    ONE("1", "显示资金分簿名和账号"),
    TWO("2", "显示实体账户名和账号"),
    THREE("3", "显示资金分簿名和实体账号"),
    FOUR("4", "显示实体账户名和资金分簿编号")
    ;

    /**
     * 操作
     */
    private String subAccPrintParm;

    /**
     * 说明
     */
    private String describe;

    private SubAccPrintParm(String subAccPrintParm, String describe){
        this.subAccPrintParm = subAccPrintParm;
        this.describe = describe;
    }

    public String getSubAccPrintParm() {
        return subAccPrintParm;
    }

    public void setSubAccPrintParm(String subAccPrintParm) {
        this.subAccPrintParm = subAccPrintParm;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
