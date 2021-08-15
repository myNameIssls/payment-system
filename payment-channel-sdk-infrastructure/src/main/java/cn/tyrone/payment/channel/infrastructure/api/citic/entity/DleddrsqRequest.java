package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 回单信息查询
 */
@Data
@SuperBuilder
public class DleddrsqRequest extends AbstractCiticBaseRequest {

    /**
     * 查询类型char(1),  1：T+0；2：T+1
     */
    private QryType qryType;
    /**
     * 账号
     */
    private String accNo;
    /**
     * 回单类型0：全部；1：转账类；2：利息类；3：收费类；4：电子缴税；5：网银结售汇；6：现金管理转账
     */
    private BillType billType;
    /**
     * 最小额度
     */
    private BigDecimal minAmt;
    /**
     * 最大额度
     */
    private BigDecimal maxAmt;
    /**
     * 起始日期char(8)，格式YYYYMMDD
     */
    private String startDate;
    /**
     * 截止日期char(8)，格式YYYYMMDD
     */
    private String endDate;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 起始记录号
     */
    private int startNo;


    @Override
    public String processing() throws RuntimeException {

        this.citicAction = CiticAction.DLEDDRSQ;

        StringBuffer xml = new StringBuffer();
        xml.append(super.statementProcessing());
        xml.append("<stream>");
        xml.append(super.actionProcessing());
        xml.append(super.elementProcessing("userName", this.userName, Boolean.TRUE));
        xml.append(super.elementProcessing("qryType", this.qryType.getQryType(), Boolean.TRUE));
        xml.append(super.elementProcessing("accNo", this.accNo, Boolean.TRUE));
        xml.append(super.elementProcessing("billType", this.billType.getBillType(), Boolean.TRUE));
        xml.append(super.elementProcessing("minAmt", this.minAmt.toString(), Boolean.TRUE));
        xml.append(super.elementProcessing("maxAmt", this.maxAmt.toString(), Boolean.TRUE));
        xml.append(super.elementProcessing("startDate", this.startDate, Boolean.TRUE));
        xml.append(super.elementProcessing("endDate", this.endDate, Boolean.TRUE));
        xml.append(super.elementProcessing("pageSize", String.valueOf(this.pageSize), Boolean.TRUE));
        xml.append(super.elementProcessing("startNo", String.valueOf(this.startNo), Boolean.TRUE));
        xml.append("</stream>");

        return xml.toString();
    }

}
