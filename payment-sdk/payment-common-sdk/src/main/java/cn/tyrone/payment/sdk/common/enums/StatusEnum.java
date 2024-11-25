package cn.tyrone.payment.sdk.common.enums;

/**
 * @author sushiran@iciyun.com 2023/1/31 12:02
 */
public enum StatusEnum implements Code {
    ENABLE(1, "有效"),
    DISABLE(0, "无效"),
    ;

    /**
     * 枚举值
     */
    private final Integer code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * @param code    枚举值
     * @param message 枚举描述
     */
    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return "";
    }

    @Override
    public String message() {
        return "";
    }
}
