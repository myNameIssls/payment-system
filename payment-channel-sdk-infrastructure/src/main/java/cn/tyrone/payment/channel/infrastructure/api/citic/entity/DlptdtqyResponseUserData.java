package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.dom4j.Node;

@Data
@SuperBuilder
public class DlptdtqyResponseUserData extends AbstractCiticBaseResponse {

    /**
     * 资金分簿编号
     */
    private String subAccNo;
    /**
     * 交易类型
     */
    private String tranType;
    /**
     * 交易日期
     */
    private String tranDate;
    /**
     * 交易时间 char(6) 格式HHMMSS
     */
    private String tranTime;
    /**
     * 柜员交易号
     */
    private String tellerNo;
    /**
     * 交易序号
     */
    private String tranSeqNo;
    /**
     * 对方账号
     */
    private String accountNo;
    /**
     * 对方账户名称
     */
    private String accountNm;
    /**
     * 对方开户行名称
     */
    private String accBnkNm;
    /**
     * 借贷标识 "D":借,"C":贷
     */
    private String loanFlag;
    /**
     * 交易金额
     */
    private String tranAmt;
    /**
     * 账户余额
     */
    private String accBalAmt;
    /**
     * 手续费金额
     */
    private String pdgAmt;
    /**
     * 附言
     */
    private String memo;
    /**
     * 打印校验码
     */
    private String verifyCode;

    public void init(Node parentNode) {

        this.subAccNo = this.getChildNodeText(parentNode, "subAccNo");
        this.tranType = this.getChildNodeText(parentNode, "tranType");
        this.tranDate = this.getChildNodeText(parentNode, "tranDate");
        this.tranTime = this.getChildNodeText(parentNode, "tranTime");
        this.tellerNo = this.getChildNodeText(parentNode, "tellerNo");
        this.tranSeqNo = this.getChildNodeText(parentNode, "tranSeqNo");
        this.accountNo = this.getChildNodeText(parentNode, "accountNo");

        this.accountNm = this.getChildNodeText(parentNode, "accountNm");
        this.accBnkNm = this.getChildNodeText(parentNode, "accBnkNm");
        this.loanFlag = this.getChildNodeText(parentNode, "loanFlag");
        this.tranAmt = this.getChildNodeText(parentNode, "tranAmt");
        this.accBalAmt = this.getChildNodeText(parentNode, "accBalAmt");
        this.pdgAmt = this.getChildNodeText(parentNode, "pdgAmt");
        this.memo = this.getChildNodeText(parentNode, "memo");
        this.verifyCode = this.getChildNodeText(parentNode, "verifyCode");

    }

}
