package cn.tyrone.payment.outreach.channel.citic.pl;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 3.15 商户资金分簿余额查询
 */
@Data
@SuperBuilder
public class DlsbalqrRequest extends AbstractCiticBaseRequest {

    /**
     * 资金分簿编号
     */
    private String subAccNo;

    @Override
    public String processing() throws RuntimeException {

        StringBuffer xml = new StringBuffer();
        xml.append(super.statementProcessing());
        xml.append("<stream>");
        xml.append(super.actionProcessing());
        xml.append(super.elementProcessing("userName", this.userName, Boolean.TRUE));
        xml.append(super.elementProcessing("mainAccNo", this.mainAccNo, Boolean.TRUE));
        xml.append(super.elementProcessing("subAccNo", this.subAccNo, Boolean.TRUE));

        xml.append("</stream>");

        return xml.toString();
    }

}
