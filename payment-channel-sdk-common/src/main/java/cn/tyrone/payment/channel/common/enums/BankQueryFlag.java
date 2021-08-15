package cn.tyrone.payment.channel.common.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 中金支付银行查询标记
 */
public enum BankQueryFlag {

    UDK("UDK", "查询支持绑定该行结算账户的银行列表"),
    U3B("U3B", "查询支持开通快捷支付业务的银行列表"),
    U6G("U6G", "查询支持企业网银支付的银行列表"),
    U6P("U6P", "查询支持个人网银支付的银行列表"),
    ALL("ALL", "查询全部银行列表"),
    ;

    public String flag;

    public String describe;

    private static Map<String, BankQueryFlag> bankQueryFlagMap = new HashMap<>();

    static {
        bankQueryFlagMap = Arrays.stream(BankQueryFlag.values()).collect(Collectors.toMap(
                bankQueryFlag -> bankQueryFlag.flag, bankQueryFlag -> bankQueryFlag
        ));
    }

    private BankQueryFlag(String flag, String describe){
        this.flag = flag;
        this.describe = describe;
    }

    public static BankQueryFlag getBankQueryFlag(String bankQueryFlag) {
        return bankQueryFlagMap.get(bankQueryFlag);
    }

}
