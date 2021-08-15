package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
public class DlinttrnRequestUserData extends AbstractCiticBaseRequest {

    /**
     * 客户流水号
     */
    private String clientID;

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
     * 支付方式 1：跨行转账；2：行内；3：企业内部转账
     */
    private PayType payType;
    /**
     * 支付时效char(1) 0：加急 1：普通
     */
    private PayFlg payFlg;

    /**
     * 付款账号
     */
    private String payAccountNo;
    /**
     * 收款账号
     */
    private String recAccountNo;
    /**
     * 收款账户名称
     */
    private String recAccountName;
    /**
     * 收款账户开户行名
     */
    private String recOpenBankName;
    /**
     * 收款账户开户行支付联行号
     */
    private String recOpenBankCode;

    /**
     * 交易金额
     */
    private BigDecimal tranAmount;


    /**
     * 附言
     */
    private String abstractt;

    /**
     * 备注
     */
    private String memo;

    /**
     * 对账编号
     */
    private String chkNum;


    @Override
    public String processing() throws RuntimeException {

        StringBuffer xml = new StringBuffer();

        xml.append(super.elementProcessing("clientID", this.clientID, Boolean.TRUE));
        xml.append(super.elementProcessing("preFlg", this.preFlg.getPreFlg(), Boolean.TRUE));

        boolean preDate = false, preTime = false;
        if (this.preFlg.equals(PreFlg.ONE)) {
            preDate = true;
            preTime = true;
        }

        xml.append(super.elementProcessing("preDate", this.preDate, preDate));
        xml.append(super.elementProcessing("preTime", this.preTime == null ? "" : this.preTime.getPreTime(), preTime));
        xml.append(super.elementProcessing("payType", this.payType.getPayType(), Boolean.TRUE));
        xml.append(super.elementProcessing("payFlg", this.payFlg.getPayFlg(), Boolean.TRUE));
        xml.append(super.elementProcessing("payAccountNo", this.payAccountNo, Boolean.TRUE));
        xml.append(super.elementProcessing("recAccountNo", this.recAccountNo, Boolean.TRUE));
        xml.append(super.elementProcessing("recAccountName", this.recAccountName, Boolean.TRUE));

        xml.append(super.elementProcessing("recOpenBankName", this.recOpenBankName, Boolean.FALSE));

        boolean recOpenBankCode = false;
        if (this.payType.equals(PayType.ONE)) {
            recOpenBankCode = true;
        }

        xml.append(super.elementProcessing("recOpenBankCode", this.recOpenBankCode, recOpenBankCode));
        xml.append(super.elementProcessing("tranAmount", this.tranAmount.toString(), Boolean.TRUE));
        xml.append(super.elementProcessing("abstract", this.abstractt, Boolean.FALSE));
        xml.append(super.elementProcessing("memo", this.memo, Boolean.FALSE));
        xml.append(super.elementProcessing("chkNum", this.chkNum, Boolean.FALSE));

        return xml.toString();

    }

    public SameBank ifSameBank(String bankNumber) {
        return SameBank.ZERO;
    }

}
