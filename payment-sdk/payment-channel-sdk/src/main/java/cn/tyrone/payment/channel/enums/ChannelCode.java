package cn.tyrone.payment.channel.enums;

import java.util.stream.Stream;

/**
 * 支付渠道唯一编码编码
 */
public enum ChannelCode {

    CPCN_ONE("CPCN-001", "中金支付-ACS记账系统"),
    CITIC_ONE("CITIC-001", "中信银行-资金账簿通道-虚拟账户体系"),
    CITIC_TOW("CITIC-002", "中信银行-现金管理系统"),

    ;

    public String uniqueValue;

    public String describe;

    private ChannelCode(String uniqueValue, String describe){
        this.uniqueValue = uniqueValue;
        this.describe = describe;
    }

    public static ChannelCode of(String uniqueValue) {
        return Stream.of(ChannelCode.values())
                .filter(paymentChannelUniqueCode -> paymentChannelUniqueCode.uniqueValue.equals(uniqueValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


}
