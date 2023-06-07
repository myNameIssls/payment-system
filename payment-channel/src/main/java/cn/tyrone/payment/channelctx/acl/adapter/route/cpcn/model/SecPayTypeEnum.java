package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.model;

import lombok.Getter;

/**
 * 支付方式二级分类
 */
@Getter
public enum SecPayTypeEnum {

    ENTERPRISE_E_BANK("1", "企业网银"),
    PERSON_E_BANK("2", "个人网银"),
    ALIPAY("3", "支付宝"),
    WECHAT("4", "微信"),
    UNION_PAY("5", "银联")
    ;

    private String secPayType;
    private String describe;

    private SecPayTypeEnum(String payType, String describe) {
        this.secPayType = payType;
        this.describe = describe;
    }

}
