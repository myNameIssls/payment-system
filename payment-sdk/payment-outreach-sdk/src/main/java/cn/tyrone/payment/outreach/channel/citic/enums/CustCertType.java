package cn.tyrone.payment.outreach.channel.citic.enums;

/**
 * 客户证件类型
 */
public enum CustCertType {

    ZERO("0", "居民身份证"),
    ONE("1", "武装警察身份证"),
    TWO("2", "护照"),
    THREE("3", "军人身份证"),
    FOUR("4", "港澳居民往来内地通行证"),
    FIVE("5", "企业代码"),
    SIX("6", "其他"),
    SEVEN("7", "台湾居民往来大陆通行证"),
    EIGHTT("8", "户口簿"),
    NINE("9", "支付结算IC卡"),
    A("A", "临时居民身份证"),
    D("D", "工商税务号"),
    ;

    /**
     * 操作
     */
    private String custCertType;

    /**
     * 说明
     */
    private String describe;

    private CustCertType(String custCertType, String describe){
        this.custCertType = custCertType;
        this.describe = describe;
    }

    public String getCustCertType() {
        return custCertType;
    }

    public void setCustCertType(String custCertType) {
        this.custCertType = custCertType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
