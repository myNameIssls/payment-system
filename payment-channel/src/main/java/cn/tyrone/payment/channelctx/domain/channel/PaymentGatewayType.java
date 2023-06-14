package cn.tyrone.payment.channelctx.domain.channel;

/**
 * 支付网关类型
 */
public enum PaymentGatewayType {

    OPEN_ACCOUNT("OPEN_ACCOUNT", "开户"),
    ACCOUNT_BALANCE("ACCOUNT_BALANCE", "余额查询"),
    ACCOUNT_DETAILS_QUERY("ACCOUNT_DETAILS_QUERY", "账户明细查询"),
    TRANSFER_ACCOUNT("TRANSFER_ACCOUNT", "转账支付"),

    ;

    public final String PAYMENT_GATEWAY_TYPE;

    public final String DESCRIBE;

    PaymentGatewayType(String paymentGatewayType, String describe) {
        this.PAYMENT_GATEWAY_TYPE = paymentGatewayType;
        this.DESCRIBE = describe;
    }
}
