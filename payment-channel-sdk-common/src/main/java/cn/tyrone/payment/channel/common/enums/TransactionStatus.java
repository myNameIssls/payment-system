package cn.tyrone.payment.channel.common.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 交易状态
 */
public enum TransactionStatus {
    TO_BE_PAID("TO_BE_PAID","待支付"),
    SUCCESS("SUCCESS", "交易成功"),
    PARTIAL_SUCCESS("PARTIAL_SUCCESS", "部分交易成功"),
    PROCESSING("PROCESSING", "交易处理中"),
    FAILURE("FAILURE", "交易失败"),
    REPEATED_TRANSACTIONS("REPEATED_TRANSACTIONS", "正常交易成功-重复交易"),
    CLOSE_TRANSACTIONS("CLOSE_TRANSACTIONS", "正常交易失败-重复交易"),
    FINISHED("FINISHED", "交易结束"),
    OVERDUE("OVERDUE", "逾期"),
    ENTRUSTED_PAYMENT_UNKNOWN("ENTRUSTED_PAYMENT_UNKNOWN", "放款成功受托支付未知-中原银行")
    ;

    /**
     * 交易状态
     */
    private String status;

    /**
     * 交易码说明
     */
    private String describe;

    private static Map<String, TransactionStatus> transactionStatusMap = new HashMap<>();

    static {
        Arrays.stream(TransactionStatus.values()).forEach(transactionStatus -> {
            transactionStatusMap.put(transactionStatus.status, transactionStatus);
        });
    }

    public static TransactionStatus getTransactionStatus(String transactionStatus) {
        return transactionStatusMap.get(transactionStatus);
    }

    TransactionStatus(String status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}
