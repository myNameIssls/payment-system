package cn.tyrone.payment.channel.common.enums;

/**
 * 证件类型
 */
public enum CertificateType {

    A("A", "身份证"),
    B("B", "户口簿"),
    C("C", "军官证"),
    D("D", "警官证"),
    E("E", "护照"),
    F("F", "港澳通行证"),
    G("G", "社会统一信用代码证"),
    H("H", "营业执照"),
    I("I", "文职干部证"),
    J("J", "士兵证"),
    K("K", "台湾通行证"),
    L("L", "其他"),

    ;

    public String type;

    public String describe;

    private CertificateType(String type, String describe){
        this.type = type;
        this.describe = describe;
    }

}
