package cn.tyrone.payment.channel.common.enums;

import lombok.Getter;

/**
 * 支付渠道配置编码
 */
@Getter
public enum ChannelConfigCode {

    CPCN_ONE("CCC-001", "记账系统"),
    CITIC_ONE("AAA-001", "通道-虚拟账户体系"),
    CITIC_TOW("AAA-002", "管理系统"),

    ;

    public String channelConfigCode;

    public String describe;

    private ChannelConfigCode(String channelConfigCode, String describe){
        this.channelConfigCode = channelConfigCode;
        this.describe = describe;
    }

}
