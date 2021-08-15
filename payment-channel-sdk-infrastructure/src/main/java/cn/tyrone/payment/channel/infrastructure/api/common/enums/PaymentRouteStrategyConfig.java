package cn.tyrone.payment.channel.infrastructure.api.common.enums;


import cn.tyrone.payment.channel.common.enums.ChannelConfigCode;
import cn.tyrone.payment.channel.common.enums.PaymentGatewayType;
import cn.tyrone.payment.channel.infrastructure.api.citic.strategy.impl.BalanceQueryRouteStrategyCiticCMS;
import cn.tyrone.payment.channel.infrastructure.api.citic.strategy.impl.TransferAccountRouteStrategyCiticCMS;
import cn.tyrone.payment.channel.infrastructure.api.cpcn.strategy.impl.AccountDetailsQueryRouteStrategyCpcnAcs;

import java.util.Arrays;

/**
 * 支付路由策略配置
 */
public enum PaymentRouteStrategyConfig {

    // 中信银行-现金管理系统-开始
    BALANCE_QUERY_CITIC_CMS(PaymentGatewayType.BALANCE_QUERY, ChannelConfigCode.CITIC_TOW, BalanceQueryRouteStrategyCiticCMS.class, "中信银行现金管理系统余额查询"),
    TRANSFER_ACCOUNT_CITIC_CMS(PaymentGatewayType.TRANSFER_ACCOUNT, ChannelConfigCode.CITIC_TOW, TransferAccountRouteStrategyCiticCMS.class, "中信银行现金管理系统转账支付"),
    // 中信银行-现金管理系统-结束

    // 中金支付-记账系统-开始
    ACCOUNT_DETAILS_QUERY_CPCN_ACS(PaymentGatewayType.ACCOUNT_DETAILS_QUERY, ChannelConfigCode.CPCN_ONE, AccountDetailsQueryRouteStrategyCpcnAcs.class, "中金支付-账户明细查询-记账系统"),
    // 中金支付-记账系统-结束
    ;

    public PaymentGatewayType paymentGatewayType;

    /**
     * 渠道配置编码
     */
    public ChannelConfigCode channelConfigCode;

    /**
     * 路由策略实现类
     */
    public Class routeStrategyImplClass;

    public String desceibe;

    PaymentRouteStrategyConfig(PaymentGatewayType paymentGatewayType, ChannelConfigCode channelConfigCode, Class routeStrategyImplClass, String describe) {
        this.paymentGatewayType = paymentGatewayType;
        this.channelConfigCode = channelConfigCode;
        this.routeStrategyImplClass = routeStrategyImplClass;
        this.desceibe = describe;
    }

    public static String getRouteStrategyServiceName(
            PaymentGatewayType paymentGatewayType, ChannelConfigCode channelConfigCode) {

        PaymentRouteStrategyConfig paymentRouteStrategyConfig1 = Arrays.stream(PaymentRouteStrategyConfig.values()).filter(paymentRouteStrategyConfig -> {
            return paymentRouteStrategyConfig.paymentGatewayType.equals(paymentGatewayType) && paymentRouteStrategyConfig.channelConfigCode.equals(channelConfigCode);
        }).findFirst().get();

        Class cls = paymentRouteStrategyConfig1.routeStrategyImplClass;
        String simpleName = cls.getSimpleName();
        simpleName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
        return simpleName;
    }

    public static void main(String[] args) {
        PaymentGatewayType paymentGatewayType = PaymentGatewayType.BALANCE_QUERY;
        ChannelConfigCode channelConfigCode = ChannelConfigCode.CITIC_TOW;

        String routeStrategyServiceName = getRouteStrategyServiceName(paymentGatewayType, channelConfigCode);
        System.out.println(routeStrategyServiceName);
    }


}
