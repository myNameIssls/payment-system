package cn.tyrone.payment.account.domain.settlement;

import cn.tyrone.payment.account.AddSettlementAccountCommand;
import lombok.Data;

/**
 * 结算账户
 */
@Data
public class SettlementAccount {

    private String settlementAccountId;
    private String customerId;  // 客户ID
    private String settlementAccountName;   // 结算账户名称
    private String settlementAccount;       // 结算账户
    private String bankId;  // 银行行号
    private String bankName;    // 银行名称
    private String bankBranchId;    // 银行支行行号
    private String bankBranchName;  // 银行支行名称

    public SettlementAccount() {}

    public SettlementAccount(String settlementAccountId, AddSettlementAccountCommand command) {
        this.settlementAccountId = settlementAccountId;
        this.customerId = command.getCustomerId();
        this.settlementAccountName = command.getSettlementAccountName();
        this.settlementAccount = command.getSettlementAccount();
        this.bankId = command.getBankId();
        this.bankName = command.getBankName();
        this.bankBranchId = command.getBankBranchId();
        this.bankBranchName = command.getBankBranchName();
    }


}
