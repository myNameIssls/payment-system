package cn.tyrone.payment.channelctx.acl.adapter.route.citic;

/**
 * 中信银行支付通道分类
 */
public enum CiticChannelType {

    ONE("CITIC-001", "资金账簿通道-虚拟账户体系"),
    TWO("CITIC-002", "现金管理系统"),
    ;

    private String channelType;

    private String describe;

    CiticChannelType(String channelType, String describe) {
        this.channelType = channelType;
        this.describe = describe;
    }

}
