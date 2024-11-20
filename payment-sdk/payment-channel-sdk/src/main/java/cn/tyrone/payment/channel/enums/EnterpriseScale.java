package cn.tyrone.payment.channel.enums;

/**
 * 企业规模
 */
public enum EnterpriseScale {

    ONE("01", "大型"),
    TWO("02", "中型"),
    THREE("03", "小型"),
    FOUR("04", "微型"),
    FIVE("05", "其它"),
    ;

    public String enterpriseScale;

    public String describe;

    private EnterpriseScale(String enterpriseScale, String describe) {
        this.enterpriseScale = enterpriseScale;
        this.describe = describe;
    }


}
