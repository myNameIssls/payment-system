package cn.tyrone.payment.channel.common.enums;

/**
 * 交易状态查询类型
 */
public enum TransactionStatusQueryType {

    ;

    private String queryType;

    private String describe;

    TransactionStatusQueryType(String queryType, String describe) {
        this.queryType = queryType;
        this.describe = describe;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
