package cn.tyrone.payment.channel.acl.adapter.route;

import cn.tyrone.payment.channel.acl.adapter.route.cpcn.strategy.impl.AccountBalanceRouteCpcnAdapter;
import cn.tyrone.payment.channel.acl.adapter.route.cpcn.strategy.impl.OpenAccountRouteCpcnAdapter;
import cn.tyrone.payment.channel.enums.ChannelCode;
import cn.tyrone.payment.channel.domain.channel.PaymentGatewayType;
import org.springframework.util.StringUtils;

import java.util.stream.Stream;

/**
 * 支付路由策略配置
 */
public enum PaymentRouteStrategyConfig {

    // 中金支付-记账系统-开始
    OPEN_ACCOUNT_CPCN_ACS(PaymentGatewayType.OPEN_ACCOUNT, ChannelCode.CPCN_ONE, OpenAccountRouteCpcnAdapter.class, "中金支付-开户接口-记账系统"),
    ACCOUNT_BALANCE_CPCN_ACS(PaymentGatewayType.ACCOUNT_BALANCE, ChannelCode.CPCN_ONE, AccountBalanceRouteCpcnAdapter.class, "中金支付-账户余额接口-记账系统"),
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
    public ChannelCode channelCode;

    /**
     * 路由策略实现类
     */
    public Class routeStrategyImplClass;

    public String desceibe;

    PaymentRouteStrategyConfig(PaymentGatewayType paymentGatewayType, ChannelCode channelCode, Class routeStrategyImplClass, String describe) {
        this.paymentGatewayType = paymentGatewayType;
        this.channelCode = channelCode;
        this.routeStrategyImplClass = routeStrategyImplClass;
        this.desceibe = describe;
    }

    public static String getPaymentRouteStrategyServiceName(
            PaymentGatewayType paymentGatewayType, ChannelCode channelCode) {

        PaymentRouteStrategyConfig paymentRouteStrategyConfig1 = Stream.of(PaymentRouteStrategyConfig.values()).filter(paymentRouteStrategyConfig -> {
            return paymentRouteStrategyConfig.paymentGatewayType.equals(paymentGatewayType) && paymentRouteStrategyConfig.channelCode.equals(channelCode);
        }).findFirst().orElseThrow(IllegalArgumentException::new);

        Class cls = paymentRouteStrategyConfig1.routeStrategyImplClass;
        String simpleName = cls.getSimpleName();
        simpleName = StringUtils.uncapitalize(simpleName);
        return simpleName;
    }

    public static void main(String[] args) {
        PaymentGatewayType paymentGatewayType = PaymentGatewayType.OPEN_ACCOUNT;
        ChannelCode channelCode = ChannelCode.CPCN_ONE;

        String routeStrategyServiceName = getPaymentRouteStrategyServiceName(paymentGatewayType, channelCode);
        System.out.println(routeStrategyServiceName);
    }


}
