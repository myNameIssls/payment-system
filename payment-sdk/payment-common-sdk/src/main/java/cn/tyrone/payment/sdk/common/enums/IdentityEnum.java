package cn.tyrone.payment.sdk.common.enums;

/**
 * 身份
 * @author sushiran@iciyun.com 2023/10/13 13:52
 */
public enum IdentityEnum {
    GUEST("GUEST", "游客"),
    CUSTOMER("CUSTOMER", "客户"),
    SHOP("SHOP", "店铺"),
    PLATFORM("PLATFORM", "运营"),
//    PREPARE("PREPARE", "备货员"),
//    PICKUP("PICKUP", "拣货员"),
//    DELIVERY("DELIVERY", "配送员"),
    ;

    /**
     * 枚举值
     */
    private final String code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * @param code    枚举值
     * @param message 枚举描述
     */
    private IdentityEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return Returns the code.
     */
    public String code() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String message() {
        return message;
    }

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code
     * @return AttachmentTypeEnum
     */
    public static IdentityEnum getByCode(String code) {
        for (IdentityEnum _enum : values()) {
            if (_enum.code().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

}
