package cn.tyrone.payment.channel.infrastructure.api.cpcn.entity;


import lombok.Getter;

/**
 * 支付方式:
 */
@Getter
public enum  PayTypeEnum {

    E_Bank("2", "网银"),
    FAST_PAYMENT("5", "快捷支付"),
    SCAN_PAYMENT("6", "正扫支付"),
    OFFICIAL_ACCOUNT_PAYMENT("8", "公众号支付"),
    UNION_NO_CARD_PAYMENT("9", "银联无卡支付"),
    APP_JUMP_PAYMENT("A", "手机 APP 跳转支付"),
    ;

    private String payType;
    private String describe;

    private PayTypeEnum(String payType, String describe) {
        this.payType = payType;
        this.describe = describe;
    }

}
