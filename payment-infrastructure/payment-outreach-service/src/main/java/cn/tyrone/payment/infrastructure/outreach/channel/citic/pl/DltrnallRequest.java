package cn.tyrone.payment.infrastructure.outreach.channel.citic.pl;

import cn.tyrone.payment.infrastructure.outreach.channel.citic.enums.CiticAction;
import cn.tyrone.payment.infrastructure.outreach.channel.citic.enums.ControlFlag;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 3.1.2 账户明细信息查询
 */
@Data
@SuperBuilder
public class DltrnallRequest extends AbstractCiticBaseRequest {

    /**
     * 最小金额
     */
    private BigDecimal lowAmount;
    /**
     * 最大金额
     */
    private BigDecimal upAmount;

    /**
     * 起始日期char(8) 格式YYYYMMDD
     */
    private String startDate;

    /**
     * 终止日期char(8) 格式YYYYMMDD
     */
    private String endDate;
    /**
     * 请求记录条数，最大为20
     */
    private int pageNumber;
    /**
     * 起始记录号
     */
    private int startRecord;
    /**
     * 控制标签char(1)，0：客户系统不兼容银行端新增返回字段1：客户系统兼容银行端新增返回字段，标签可空
     */
    private ControlFlag controlFlag;

    @Override
    public String processing() throws RuntimeException {

        this.citicAction = CiticAction.DLTRNALL;

        StringBuffer xml = new StringBuffer();
        xml.append(super.statementProcessing());
        xml.append("<stream>");
        xml.append(super.actionProcessing());
        xml.append(super.elementProcessing("userName", this.userName, Boolean.TRUE));
        xml.append(super.elementProcessing("accountNo", this.accountNo, Boolean.TRUE));
        xml.append(super.elementProcessing("lowAmount", this.lowAmount.toString(), Boolean.TRUE));
        xml.append(super.elementProcessing("upAmount", this.upAmount.toString(), Boolean.TRUE));
        xml.append(super.elementProcessing("startDate", this.startDate, Boolean.TRUE));
        xml.append(super.elementProcessing("endDate", this.endDate, Boolean.TRUE));
        xml.append(super.elementProcessing("pageNumber", String.valueOf(this.pageNumber), Boolean.TRUE));
        xml.append(super.elementProcessing("startRecord", String.valueOf(this.startRecord), Boolean.TRUE));
        xml.append(super.elementProcessing("controlFlag", this.controlFlag.getControlFlag(), Boolean.FALSE));

        xml.append("</stream>");

        return xml.toString();
    }

}
