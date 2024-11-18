package cn.tyrone.payment.account;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddSettlementAccountCommand implements Serializable {

    private String customerId;  // 客户ID
    private String settlementAccountName;   // 结算账户名称
    private String settlementAccount;       // 结算账户
    private String bankId;  // 银行行号
    private String bankName;    // 银行名称
    private String bankBranchId;    // 银行支行行号
    private String bankBranchName;  // 银行支行名称

}
