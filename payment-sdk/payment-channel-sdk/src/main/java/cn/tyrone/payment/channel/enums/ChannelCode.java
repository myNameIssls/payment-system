package cn.tyrone.payment.channel.enums;

import java.util.stream.Stream;

/**
 * 支付渠道唯一编码编码
 */
public enum ChannelCode {

    CPCN_ONE("CCC-001", "记账系统"),
    CITIC_ONE("AAA-001", "通道-虚拟账户体系"),
    CITIC_TOW("AAA-002", "管理系统"),

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
