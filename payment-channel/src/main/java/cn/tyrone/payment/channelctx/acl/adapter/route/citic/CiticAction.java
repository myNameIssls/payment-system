package cn.tyrone.payment.channelctx.acl.adapter.route.citic;

/**
 * 中信银行操作类型
 */
public enum CiticAction {

    DLBREGSN("DLBREGSN", "会员注册", CiticChannelType.ONE),
    DLSBALQR("DLSBALQR", "商户资金分簿余额查询", CiticChannelType.ONE),
    DLMDETRN("DLMDETRN ", "强制转账", CiticChannelType.ONE),
    DLINTFCS("DLINTFCS ", "平台出金", CiticChannelType.ONE),
    DLCIDSTT("DLCIDSTT", "交易状态查询", CiticChannelType.ONE),
    DLPTDTQY("DLPTDTQY", "非登录打印明细查询", CiticChannelType.ONE),

    DLBALQRY("DLBALQRY", "余额查询", CiticChannelType.TWO),
    DLINTTRN("DLINTTRN", "支付转账", CiticChannelType.TWO),
    DLEDDRSQ("DLEDDRSQ", "回单信息查询", CiticChannelType.TWO),
    DLEDCDTD("DLEDCDTD", "电子回单下载", CiticChannelType.TWO),
    DLTRNALL("DLTRNALL", "账户明细信息查询", CiticChannelType.TWO),

    ;

    /**
     * 操作
     */
    private String action;

    /**
     * 说明
     */
    private String describe;

    private CiticChannelType channelType;

    private CiticAction(String action, String describe, CiticChannelType channelType){
        this.action = action;
        this.describe = describe;
        this.channelType = channelType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
