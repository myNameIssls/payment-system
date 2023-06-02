package cn.tyrone.payment.channelctx.acl.adapter.route;

import cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.OpenAccountRouteCpcnAdapter;
import cn.tyrone.payment.channelctx.domain.PaymentChannelUniqueCode;
import cn.tyrone.payment.channelctx.domain.PaymentGatewayType;
import org.springframework.util.StringUtils;

import java.util.stream.Stream;

/**
 * 支付路由策略配置
 */
public enum PaymentRouteStrategyConfig {

    // 中金支付-记账系统-开始
    OPEN_ACCOUNT_CPCN_ACS(PaymentGatewayType.OPEN_ACCOUNT, PaymentChannelUniqueCode.CPCN_ONE, OpenAccountRouteCpcnAdapter.class, "中金支付-开户接口-记账系统"),
//    ACCOUNT_DETAILS_QUERY_CPCN_ACS(PaymentGatewayType.ACCOUNT_DETAILS_QUERY, PaymentChannelUniqueCode.CPCN_ONE, AccountDetailsQueryRouteStrategyCpcnAcs.class, "中金支付-账户明细查询-记账系统"),
    // 中金支付-记账系统-结束

    // 中信银行-现金管理系统-开始
//    BALANCE_QUERY_CITIC_CMS(PaymentGatewayType.BALANCE_QUERY, PaymentChannelUniqueCode.CITIC_TOW, BalanceQueryRouteStrategyCiticCMS.class, "中信银行现金管理系统余额查询"),
//    TRANSFER_ACCOUNT_CITIC_CMS(PaymentGatewayType.TRANSFER_ACCOUNT, PaymentChannelUniqueCode.CITIC_TOW, TransferAccountRouteStrategyCiticCMS.class, "中信银行现金管理系统转账支付"),
    // 中信银行-现金管理系统-结束

    ;

    public PaymentGatewayType paymentGatewayType;

    /**
     * 渠道配置编码
     */
    public PaymentChannelUniqueCode paymentChannelUniqueCode;

    /**
     * 路由策略实现类
     */
    public Class routeStrategyImplClass;

    public String desceibe;

    PaymentRouteStrategyConfig(PaymentGatewayType paymentGatewayType, PaymentChannelUniqueCode paymentChannelUniqueCode, Class routeStrategyImplClass, String describe) {
        this.paymentGatewayType = paymentGatewayType;
        this.paymentChannelUniqueCode = paymentChannelUniqueCode;
        this.routeStrategyImplClass = routeStrategyImplClass;
        this.desceibe = describe;
    }

    public static String getPaymentRouteStrategyServiceName(
            PaymentGatewayType paymentGatewayType, PaymentChannelUniqueCode paymentChannelUniqueCode) {

        PaymentRouteStrategyConfig paymentRouteStrategyConfig1 = Stream.of(PaymentRouteStrategyConfig.values()).filter(paymentRouteStrategyConfig -> {
            return paymentRouteStrategyConfig.paymentGatewayType.equals(paymentGatewayType) && paymentRouteStrategyConfig.paymentChannelUniqueCode.equals(paymentChannelUniqueCode);
        }).findFirst().orElseThrow(IllegalArgumentException::new);

        Class cls = paymentRouteStrategyConfig1.routeStrategyImplClass;
        String simpleName = cls.getSimpleName();
        simpleName = StringUtils.uncapitalize(simpleName);
        return simpleName;
    }

    public static void main(String[] args) {
        PaymentGatewayType paymentGatewayType = PaymentGatewayType.OPEN_ACCOUNT;
        PaymentChannelUniqueCode paymentChannelUniqueCode = PaymentChannelUniqueCode.CPCN_ONE;

        String routeStrategyServiceName = getPaymentRouteStrategyServiceName(paymentGatewayType, paymentChannelUniqueCode);
        System.out.println(routeStrategyServiceName);
    }


}
