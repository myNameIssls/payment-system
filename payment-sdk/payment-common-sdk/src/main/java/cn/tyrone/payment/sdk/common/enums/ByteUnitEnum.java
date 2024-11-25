package cn.tyrone.payment.sdk.common.enums;


/**
 * @program: tangcang2-parent
 * @ClassName ByteUnitEnum
 * @description: 字节单位
 * @author: gaojianjun
 * @create: 2022-10-26 13:38
 * @version: 1.0
 **/
public enum ByteUnitEnum {

    B("B","字节"),
    KB("KB","千字节"),
    MB("MB","兆字节"),
    GB("GB","千兆字节"),
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
    private ByteUnitEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
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
     * @return ByteUnitEnum
     */
    public static ByteUnitEnum getByCode(String code) {
        for (ByteUnitEnum _enum : values()) {
            if (_enum.getCode().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举
     *
     * @return List<ByteUnitEnum>
     */
    public static java.util.List<ByteUnitEnum> getAllEnum() {
        java.util.List<ByteUnitEnum> list = new java.util.ArrayList<ByteUnitEnum>(
                values().length);
        for (ByteUnitEnum _enum : values()) {
            list.add(_enum);
        }
        return list;
    }

    /**
     * 获取全部枚举值
     *
     * @return List<String>
     */
    public static java.util.List<String> getAllEnumCode() {
        java.util.List<String> list = new java.util.ArrayList<String>(values().length);
        for (ByteUnitEnum _enum : values()) {
            list.add(_enum.code());
        }
        return list;
    }

    /**
     * 通过code获取msg
     *
     * @param code 枚举值
     * @return
     */
    public static String getMsgByCode(String code) {
        if (code == null) {
            return null;
        }
        ByteUnitEnum _enum = getByCode(code);
        if (_enum == null) {
            return null;
        }
        return _enum.getMessage();
    }

    /**
     * 获取枚举code
     *
     * @param _enum
     * @return
     */
    public static String getCode(ByteUnitEnum _enum) {
        if (_enum == null) {
            return null;
        }
        return _enum.getCode();
    }
}
