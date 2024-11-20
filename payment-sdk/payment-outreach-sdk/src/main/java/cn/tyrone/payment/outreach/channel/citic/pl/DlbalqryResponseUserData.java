package cn.tyrone.payment.outreach.channel.citic.pl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dom4j.Node;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DlbalqryResponseUserData extends AbstractCiticBaseResponse {

    /**
     * 账号
     */
    private String accountNo;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 币种
     */
    private String currencyID;

    /**
     * 开户行名称
     */
    private String openBankName;

    /**
     * 最近交易日
     */
    private String lastTranDate;

    /**
     *
     */
    private String usableBalance;

    /**
     * 账号余额
     */
    private String balance;

    /**
     * 冻结（或看管）金额
     */
    private String forzenAmt;
//    /**
//     * 冻结
//     * A：正常；D:睡眠；F:冻结，仅当查询账号为信银国际账号时返回
//     */
//    private String frozenFlag;
//    /**
//     * 账户类型
//     * ST:活期储蓄；IM:活期支票，仅当查询账号为信银国际账号时返回
//     */
//    private String accountType;
//    /**
//     * 法透额度 仅当查询账号为信银国际账号时返回
//     */
//    private String lawptLmt;

    public void init(Node parentNode) {

        this.status = this.getChildNodeText(parentNode, "status");
        this.statusText = this.getChildNodeText(parentNode, "statusText");
        this.accountNo = this.getChildNodeText(parentNode, "accountNo");
        this.accountName = this.getChildNodeText(parentNode, "accountName");
        this.currencyID = this.getChildNodeText(parentNode, "currencyID");
        this.openBankName = this.getChildNodeText(parentNode, "openBankName");
        this.lastTranDate = this.getChildNodeText(parentNode, "lastTranDate");
        this.usableBalance = this.getChildNodeText(parentNode, "usableBalance");
        this.balance = this.getChildNodeText(parentNode, "balance");
        this.forzenAmt = this.getChildNodeText(parentNode, "forzenAmt");
//        this.frozenFlag = this.getChildNodeText(parentNode, "frozenFlag");
//        this.accountType = this.getChildNodeText(parentNode, "accountType");
//        this.lawptLmt = this.getChildNodeText(parentNode, "lawptLmt");
    }


}
