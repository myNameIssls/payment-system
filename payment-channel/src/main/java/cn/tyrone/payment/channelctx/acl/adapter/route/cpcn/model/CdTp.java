package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.model;

/**
 * 证件类型
 */
public enum CdTp {

    A("A", "身份证"),
    B("B", "户口簿"),
    C("C", "军官证"),
    D("D", "警官证"),
    E("E", "护照"),
    F("F", "港澳通行证"),
    G("G", "社会统一信用代码"),
    H("H", "营业执照"),
    I("I", "文职干部证"),
    J("J", "士兵证"),
    K("K", "台湾通行证"),
    L("L", "其它"),
    ;
    public String cdTp;

    public String describe;

    CdTp(String cdTp, String describe) {
        this.cdTp = cdTp;
        this.describe = describe;
    }
}
