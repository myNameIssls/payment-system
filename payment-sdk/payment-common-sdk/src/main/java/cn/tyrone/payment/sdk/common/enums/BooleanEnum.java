package cn.tyrone.payment.sdk.common.enums;


import lombok.Getter;

/**
 * @author sushiran@iciyun.com 2023/8/16 17:02
 */
@Getter
public enum BooleanEnum implements Code {
    NO(0, "否"),
    YES(1, "是"),
    ;

    /**
     * 枚举值
     */
    private final int code;

    /**
     * 枚举描述
     */
    private final String message;

    /**
     * @param code    枚举值
     * @param message 枚举描述
     */
    private BooleanEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return Returns the code.
     */
    public String code() {
        return String.valueOf(this.code);
    }

    /**
     * @return Returns the message.
     */
    public String message() {
        return message;
    }
}
