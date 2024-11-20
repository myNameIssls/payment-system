package cn.tyrone.payment.outreach.channel.citic.pl;

import cn.tyrone.payment.outreach.channel.citic.enums.CashTransferFlag;
import cn.tyrone.payment.outreach.channel.citic.enums.CreditDebitFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dom4j.Node;

/**
 * 会员注册
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DltrnallResponseUserData extends AbstractCiticBaseResponse {

    /**
     * <pre>
     * 交易日期 char(8) 格式YYYYMMDD
     * </pre>
     */
    private String tranDate;

    /**
     * <pre>
     * 交易时间 char(6) 格式hhmmss
     * </pre>
     */
    private String tranTime;

    /**
     * <pre>
     * 柜员交易号 char(14)
     * </pre>
     */
    private String tranNo;

    /**
     * <pre>
     * 总交易流水号 char(13)
     * </pre>
     */
    private String sumTranNo;

    /**
     * <pre>
     * 交易金额 decimal(15,2)
     * </pre>
     */
    private String tranAmount;

    /**
     * <pre>
     * 借贷标识 借：D，贷：C char(1)
     * </pre>
     */
    private CreditDebitFlag creditDebitFlag;

    /**
     * <pre>
     * 对方账号 varchar(32)
     * </pre>
     */
    private String oppAccountNo;

    /**
     * <pre>
     * 对方账户名称 varchar(122)
     * </pre>
     */
    private String oppAccountName;

    /**
     * <pre>
     * 对方开户行名 varchar(122)
     * </pre>
     */
    private String oppOpenBankName;

    /**
     * <pre>
     * 附言 varchar(102)
     * </pre>
     */
    private String abstractt;

    /**
     * <pre>
     * 现转标识 0：现金；1：转帐 char(1)
     * </pre>
     */
    private CashTransferFlag cashTransferFlag;

    /**
     * <pre>
     * 网银制单员 char(20)
     * </pre>
     */
    private String opId;

    /**
     * <pre>
     * 制单员姓名 varchar(20)
     * </pre>
     */
    private String opName;

    /**
     * <pre>
     * 网银审核员char(20)
     * </pre>
     */
    private String ckId;

    /**
     * <pre>
     * 审核员姓名varchar(20)
     * </pre>
     */
    private String ckName;

    /**
     * <pre>
     * 账户余额 decimal(15,2)
     * </pre>
     */
    private String balance;

    /**
     * <pre>
     * 起息日期 char(8)
     * </pre>
     */
    private String valueDate;

    /**
     * <pre>
     * 主机交易码 varchar(7)
     * </pre>
     */
    private String hostTranCode;

    /**
     * <pre>
     * 退汇日期 char(8)，格式YYYYMMDD
     * </pre>
     */
    private String e3rtDate;

    /**
     * <pre>
     * 退汇标志 char(1)，0：退汇；1：非退汇
     * </pre>
     */
    private String e3rtFlag;

    /**
     * <pre>
     * 付款原有金额decimal (15,2)，仅当查询账号为信银国际账号时返回
     * </pre>
     */
    private String oriDebitAmt;

    /**
     * <pre>
     * 付款原有币种char(2) ，仅当查询账号为信银国际账号时返回
     * </pre>
     */
    private String oriDebitCry;

    /**
     * <pre>
     * 收款原有金额decimal(15,2) ，仅当查询账号为信银国际账号时返回
     * </pre>
     */
    private String oriCreditAmt;

    /**
     * <pre>
     * 收款原有币种char(2) ，仅当查询账号为信银国际账号时返回
     * </pre>
     */
    private String oriCreditCry;

    /**
     * <pre>
     * 交易币种char(2) ，仅当查询账号为信银国际账号时返回
     * </pre>
     */
    private String traCryType;

    /**
     * <pre>
     * 信银国际交易参考号varchar(35) ，仅当查询账号为信银国际账号时返回
     * </pre>
     */
    private String tranRefNo;

    /**
     * <pre>
     * 客户流水号char(20)，当查询账号为信银国际账号时返回
     * </pre>
     */
    private String clientId;

    /**
     * <pre>
     * 对账编号char(20)，当客户上送controlFlag标签并且字段值为1时返回
     * </pre>
     */
    private String chkNum;

    /**
     * <pre>
     * 关联交易日志号char(14)，当客户上送controlFlag标签并且字段值为1时返回
     * </pre>
     */
    private String rlTranNo;

    /**
     * <pre>
     * 冲账对方交易日期char(8)，当客户上送controlFlag标签并且字段值为1时返回
     * </pre>
     */
    private String rfTranDt;

    /**
     * <pre>
     * 冲账对方柜员交易号char(14)，当客户上送controlFlag标签并且字段值为1时返回
     * </pre>
     */
    private String rfTranNo;


    public void init(Node parentNode) {

        this.tranDate = this.getChildNodeText(parentNode, "tranDate");
        this.tranTime = this.getChildNodeText(parentNode, "tranTime");
        this.tranNo = this.getChildNodeText(parentNode, "tranNo");
        this.sumTranNo = this.getChildNodeText(parentNode, "sumTranNo");
        this.tranAmount = this.getChildNodeText(parentNode, "tranAmount");
        String creditDebitFlagVal = this.getChildNodeText(parentNode, "creditDebitFlag");
        this.creditDebitFlag = CreditDebitFlag.D;
        if (creditDebitFlagVal.equals(CreditDebitFlag.C.getCreditDebitFlag())) {
            this.creditDebitFlag = CreditDebitFlag.C;
        }
        this.oppAccountNo = this.getChildNodeText(parentNode, "oppAccountNo");
        this.oppAccountName = this.getChildNodeText(parentNode, "oppAccountName");
        this.abstractt = this.getChildNodeText(parentNode, "abstract");
        String cashTransferFlag = this.getChildNodeText(parentNode, "cashTransferFlag");
        this.cashTransferFlag = CashTransferFlag.ONE;
        if (cashTransferFlag.equals(CashTransferFlag.ZERO)) {
            this.cashTransferFlag = CashTransferFlag.ZERO;
        }
        this.opId = this.getChildNodeText(parentNode, "opId");
        this.opName = this.getChildNodeText(parentNode, "opName");
        this.ckId = this.getChildNodeText(parentNode, "ckId");
        this.ckName = this.getChildNodeText(parentNode, "ckName");
        this.balance = this.getChildNodeText(parentNode, "balance");
        this.valueDate = this.getChildNodeText(parentNode, "valueDate");
        this.hostTranCode = this.getChildNodeText(parentNode, "hostTranCode");
        this.e3rtDate = this.getChildNodeText(parentNode, "e3rtDate");
        this.e3rtFlag = this.getChildNodeText(parentNode, "e3rtFlag");
//        this.oriDebitAmt = this.getChildNodeText(parentNode, "oriDebitAmt");
//        this.oriDebitCry = this.getChildNodeText(parentNode, "oriDebitCry");
//        this.oriCreditAmt = this.getChildNodeText(parentNode, "oriCreditAmt");
//        this.oriCreditCry = this.getChildNodeText(parentNode, "oriCreditCry");
//        this.traCryType = this.getChildNodeText(parentNode, "traCryType");
//        this.tranRefNo = this.getChildNodeText(parentNode, "tranRefNo");
//        this.clientId = this.getChildNodeText(parentNode, "clientId");
        this.chkNum = this.getChildNodeText(parentNode, "chkNum");
        this.rlTranNo = this.getChildNodeText(parentNode, "rlTranNo");
        this.rfTranDt = this.getChildNodeText(parentNode, "rfTranDt");
        this.rfTranNo = this.getChildNodeText(parentNode, "rfTranNo");

    }

}
