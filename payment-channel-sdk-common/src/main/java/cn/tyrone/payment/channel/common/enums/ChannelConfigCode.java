package cn.tyrone.payment.channel.common.enums;

import lombok.Getter;

/**
 * 支付渠道配置编码
 */
@Getter
public enum ChannelConfigCode {

    CITIC_ONE("CITIC-001", "中信银行-资金账簿通道-虚拟账户体系"),
    CITIC_TOW("CITIC-002", "中信银行-现金管理系统"),
    CPCN_ONE("CPCN-001", "中金支付-ACS记账系统"),

    ;

    public String channelConfigCode;

    public String describe;

    private ChannelConfigCode(String channelConfigCode, String describe){
        this.channelConfigCode = channelConfigCode;
        this.describe = describe;
    }

}
