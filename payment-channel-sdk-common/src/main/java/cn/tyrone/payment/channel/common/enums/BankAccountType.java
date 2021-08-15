package cn.tyrone.payment.channel.common.enums;

/**
 * 账户类型
 */
public enum BankAccountType {

    ONE("1", "对公"),
    TWO("2", "对私"),

    ;

    public String bankAccountType;

    private String describe;

    BankAccountType(String bankAccountType, String describe) {
        this.bankAccountType = bankAccountType;
        this.describe = describe;
    }
}
