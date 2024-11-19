package cn.tyrone.payment.infrastructure.outreach.channel.citic.pl;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 3.42非登录打印明细查询
 */
@Data
@SuperBuilder
public class DlptdtqyRequest extends AbstractCiticBaseRequest {

    /**
     * 资金分簿编号
     */
    private String subAccNo;
    /**
     * 起始日期 格式YYYYMMDD
     */
    private String startDate;
    /**
     * 终止日期
     * 格式YYYYMMDD
     */
    private String endDate;
    /**
     * 起始记录号
     */
    private int startRecord;
    /**
     * 请求记录条数，最大为10
     */
    private int pageNumber;

    @Override
    public String processing() throws RuntimeException {

        StringBuffer xml = new StringBuffer();
        xml.append(super.statementProcessing());
        xml.append("<stream>");
        xml.append(super.actionProcessing());
        xml.append(super.elementProcessing("userName", this.userName, Boolean.TRUE));
        xml.append(super.elementProcessing("mainAccNo", this.mainAccNo, Boolean.TRUE));
        xml.append(super.elementProcessing("subAccNo", this.subAccNo, Boolean.TRUE));

        xml.append(super.elementProcessing("startDate", this.startDate, Boolean.TRUE));
        xml.append(super.elementProcessing("endDate", this.endDate, Boolean.TRUE));
        xml.append(super.elementProcessing("startRecord", String.valueOf(this.startRecord), Boolean.TRUE));
        xml.append(super.elementProcessing("pageNumber", String.valueOf(this.pageNumber), Boolean.TRUE));

        xml.append("</stream>");

        return xml.toString();
    }
}
