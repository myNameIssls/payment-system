package cn.tyrone.payment.channelctx.domain.channel;

import lombok.Getter;

/**
 * 支付渠道唯一编码编码
 */
@Getter
public enum PaymentChannelUniqueCode {

    CITIC_ONE("CITIC-001", "中信银行-资金账簿通道-虚拟账户体系"),
    CITIC_TOW("CITIC-002", "中信银行-现金管理系统"),
    CPCN_ONE("CPCN-001", "中金支付-ACS记账系统"),

    ;

    public String channelConfigCode;

    public String describe;

    private PaymentChannelUniqueCode(String channelConfigCode, String describe){
        this.channelConfigCode = channelConfigCode;
        this.describe = describe;
    }


}
