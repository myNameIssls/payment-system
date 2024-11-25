package cn.tyrone.payment.sdk.common.enums;


/**
 * @author
 */
public enum SexEnum implements Code {
    MAN(0, "先生"),
    WOMAN(1, "女生"),
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
    SexEnum(Integer code, String message) {
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
