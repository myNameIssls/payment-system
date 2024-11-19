package cn.tyrone.payment.channel.infrastructure.api.citic.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 回单类型
 * 0：全部；1：转账类；2：利息类；3：收费类；4：电子缴税；5：网银结售汇；6：现金管理转账
 * 100000：存款回单；100001：取款回单；200000：转账回单；200001：缴税回单；300000：收费回单；400000:定期回单；400001：活期回单
 */
public enum BillType {

    ZERO("0", "全部"),
    ONE("1", "转账类"),
    TWO("2", "利息类"),
    THREE("3", "收费类"),
    FOUR("4", "电子缴税"),
    FIVE("5", "网银结售汇"),
    SIX("6", "现金管理转账"),

    SEVEN("100000", "存款回单"),
    EIGHT("100001", "取款回单"),
    NINE("200000", "转账回单"),
    TEN("200001", "缴税回单"),
    ELEVEN("300000", "收费回单"),
    TWELVE("400000", "定期回单"),
    THIRTEEN("400001", "活期回单"),
    ;

    /**
     * 操作
     */
    private String billType;

    /**
     * 说明
     */
    private String describe;

    private static Map<String, BillType> enumMap = new HashMap<>();

    private BillType(String billType, String describe) {
        this.billType = billType;
        this.describe = describe;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    static {
        for (BillType billType : BillType.values()) {
            enumMap.put(billType.getBillType(), billType);
        }
    }

    public static BillType getBillType(String billType) {
        return enumMap.get(billType);
    }

}
