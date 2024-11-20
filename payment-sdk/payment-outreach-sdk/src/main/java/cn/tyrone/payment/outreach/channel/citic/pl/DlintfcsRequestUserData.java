package cn.tyrone.payment.outreach.channel.citic.pl;

import cn.tyrone.payment.outreach.channel.citic.enums.PayFlg;
import cn.tyrone.payment.outreach.channel.citic.enums.PreFlg;
import cn.tyrone.payment.outreach.channel.citic.enums.PreTime;
import cn.tyrone.payment.outreach.channel.citic.enums.SameBank;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
public class DlintfcsRequestUserData extends AbstractCiticBaseRequest {

    /**
     * 客户流水号
     */
    private String clientID;
    /**
     * 付款账号
     */
    private String accountNo;
    /**
     * 收款账号
     */
    private String recvAccNo;
    /**
     * 收款账户名称
     */
    private String recvAccNm;

    /**
     * 交易金额
     */
    private BigDecimal tranAmt;

    /**
     * 是否本行
     */
    private SameBank sameBank;
    /**
     * 支付时效char(1) 0：加急 1：普通
     */
    private PayFlg payFlg;

    /**
     * 收款账户开户行支付联行号
     */
    private String recvTgfi;
    /**
     * 收款账户开户行名
     */
    private String recvBankNm;
    /**
     * 附言
     */
    private String memo;
    /**
     * 预约标志
     */
    private PreFlg preFlg;
    /**
     * 预约日期 格式：YYYYMMDD
     * 预约时非空
     */
    private String preDate;
    /**
     * 预约时间格式：hhmmss
     * 预约时非空，只限100000、120000、140000、160000四个时间点
     */
    private PreTime preTime;
    /**
     * 预留字段
     */
    private String field;

    @Override
    public String processing() throws RuntimeException {

        StringBuffer xml = new StringBuffer();

        xml.append(super.elementProcessing("clientID", this.clientID, Boolean.TRUE));
        xml.append(super.elementProcessing("accountNo", this.accountNo, Boolean.TRUE));
        xml.append(super.elementProcessing("recvAccNo", this.recvAccNo, Boolean.TRUE));

        xml.append(super.elementProcessing("recvAccNm", this.recvAccNm, Boolean.TRUE));
        xml.append(super.elementProcessing("tranAmt", this.tranAmt.toString(), Boolean.TRUE));
        xml.append(super.elementProcessing("sameBank", this.sameBank.getSameBank(), Boolean.TRUE));
        xml.append(super.elementProcessing("payFlg", this.payFlg.getPayFlg(), Boolean.TRUE));

        boolean recvTgfiIfMust = this.sameBank.equals(SameBank.ZERO) ? false : true;

        xml.append(super.elementProcessing("recvTgfi", this.recvTgfi, recvTgfiIfMust));
        xml.append(super.elementProcessing("recvBankNm", this.recvBankNm, Boolean.FALSE));

        xml.append(super.elementProcessing("memo", this.memo, Boolean.FALSE));
        xml.append(super.elementProcessing("preFlg", this.preFlg.getPreFlg(), Boolean.TRUE));
        xml.append(super.elementProcessing("preDate", this.preDate, Boolean.TRUE));
        xml.append(super.elementProcessing("preTime", this.preTime.getPreTime(), Boolean.TRUE));
        xml.append(super.elementProcessing("field", this.field, Boolean.TRUE));

        return xml.toString();

    }

    public SameBank ifSameBank(String bankNumber) {
        return SameBank.ZERO;
    }

}
