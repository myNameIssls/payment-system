package cn.tyrone.payment.channel.common.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 支付通道编码
 */
public enum PaymentChannelType {

    /**
     * 中金支付
     */
    CPCN("CPCN", "中金支付"),

    /**
     * 兴业银行
     */
    CIB("CIB", "兴业银行"),

    /**
     * 光大银行
     */
    CEB("CEB", "光大银行"),

    /**
     * 中原银行
     */
    ZYB("ZYB", "中原银行"),

    /**
     * 线下支付
     */
    XX("XX", "线下支付"),

    /**
     * 中信银行
     */
    CITIC("CITIC", "中信银行"),

    ;

    public String paymentChannelType;

    public String describe;

    private static Map<String, PaymentChannelType> paymentChannelTypeMap = new HashMap<>();

    static {
        paymentChannelTypeMap = Arrays.stream(PaymentChannelType.values()).collect(
                Collectors.toMap(
                    paymentChannelType -> paymentChannelType.getPaymentChannelType(),
                    paymentChannelType -> paymentChannelType
        ));
    }

    private PaymentChannelType(String paymentChannelType, String describe){
        this.paymentChannelType = paymentChannelType;
        this.describe = describe;
    }

    public static PaymentChannelType getPaymentChannelType(String paymentChannelType){
        return paymentChannelTypeMap.get(paymentChannelType);
    }

    public String getPaymentChannelType() {
        return paymentChannelType;
    }

    public void setPaymentChannelType(String paymentChannelType) {
        this.paymentChannelType = paymentChannelType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
