package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 强制转账请求报文对象
 */
@Data
@SuperBuilder
public class DlmdetrnRequest extends AbstractCiticBaseRequest {

    /**
     * 客户流水号
     */
    private String clientID;

    /**
     * 付款账号
     */
    private String payAccNo;

    /**
     * 转账类型
     */
    private TranType tranType;

    /**
     * 收款账号
     */
    private String recvAccNo;
    /**
     * 收款账号
     */
    private String recvAccNm;
    /**
     * 交易金额decimal(15,2)
     */
    private String tranAmt;
    /**
     * 冻结编号varchar(22)，转账类型为“解冻”或“解冻支付”时，必输
     */
    private String freezeNo;
    /**
     * 原冻结金额decimal(15,2) ，可空，进行续冻结时必输
     */
    private String ofreezeamt;
    /**
     * 附言
     */
    private String memo;
    /**
     * 转账时效标识char (1)，0：异步交易；1：同步交易
     */
    private TranFlag tranFlag;


    @Override
    public String processing() throws RuntimeException {

        StringBuffer xml = new StringBuffer();
        xml.append(super.statementProcessing());
        xml.append("<stream>");
        xml.append(super.actionProcessing());
        xml.append(super.elementProcessing("userName", this.userName, Boolean.TRUE));
        xml.append(super.elementProcessing("clientID", this.clientID, Boolean.TRUE));
        xml.append(super.elementProcessing("accountNo", this.mainAccNo, Boolean.TRUE));
        xml.append(super.elementProcessing("payAccNo", this.payAccNo, Boolean.TRUE));
        xml.append(super.elementProcessing("tranType", this.tranType.getTranType(), Boolean.TRUE));

        boolean recvAccNoIfMust = this.tranType.equals(TranType.BR) ? false : true;
        xml.append(super.elementProcessing("recvAccNo", this.recvAccNo, recvAccNoIfMust));
        boolean recvAccNmIfMust = this.tranType.equals(TranType.BR) ? false : true;
        xml.append(super.elementProcessing("recvAccNmIfMust", this.recvAccNm, recvAccNmIfMust));

        xml.append(super.elementProcessing("tranAmt", this.tranAmt, Boolean.TRUE));

        boolean freezeNoIfMust = true;
        if (this.tranType.equals(TranType.BG) || this.tranType.equals(TranType.BH)) {
            freezeNoIfMust = false;
        }

        xml.append(super.elementProcessing("freezeNo", this.freezeNo, freezeNoIfMust));
        xml.append(super.elementProcessing("memo", this.memo, Boolean.FALSE));
        xml.append(super.elementProcessing("tranFlag", this.tranFlag.getTranFlag(), Boolean.TRUE));
        xml.append("</stream>");

        return xml.toString();
    }
}
