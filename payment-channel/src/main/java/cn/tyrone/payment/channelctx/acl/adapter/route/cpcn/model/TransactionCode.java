package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.model;

/**
 * 交易码
 */
public enum TransactionCode {

    T1001("T1001", "中金-开户"),
    T1004("T1004", "中金-结算账户维护-绑卡"),
    T1005("T1005", "中金-账户余额查询"),
    T1007("T1007", "中金-结算账户信息查询"),
    T1008("T1008", "中金-网银列表"),
    T1111("T1111", "中金-银行卡三要素认证"),
    T1131("T1131", "中金-打款验证申请"),
    T1132("T1132", "中金-打款验证"),
    T2004("T2004", "中金-银商出金-提现"),
    T2006("T2006", "中金-网银充值"),
    T2031("T2031", "中金-入金直通车"),
    T2008("T2008", "中金-入金结果通知"),
    T2014("T2014", "中金-查询电子转账凭证"),
    T2019("T2019", "中金-打印电子转账凭证"),
    T3001("T3001", "中金-冻结/解冻"),
    T3004("T3004", "中金-订单支付"),
    T3005("T3005", "中金-批量订单支付"),
    T3061("T3061", "中金-入金支付-集成交易_异步"),
    T3068("T3068", "中金-入金支付-集成交易_交易结果通知"),
    T4001("T4001", "中金-快捷支付"),
    T4004("T4004", "中金-快捷充值申请"),
    T9002("T9002", "中金-查询出入金明细"),
    T1018("T1018", "中金-查询可代付出金额度")
    ;

    /**
     * 交易码
     */
    public String transactionCode;

    /**
     * 交易码说明
     */
    public String describe;

    TransactionCode(String transactionCode, String describe) {
        this.transactionCode = transactionCode;
        this.describe = describe;
    }
}
