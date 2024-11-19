package cn.tyrone.payment.infrastructure.outreach.channel.citic.pl;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * 平台出金请求
 */
@Data
@SuperBuilder
public class DlintfcsRequest extends AbstractCiticBaseRequest {

    /**
     * 总笔数
     */
    private int totalNumber;
    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 出金用户信息
     */
    private List<DlintfcsRequestUserData> userDataList;

    @Override
    public String processing() throws RuntimeException {
        StringBuffer xml = new StringBuffer();
        xml.append(super.statementProcessing());
        xml.append("<stream>");
        xml.append(super.actionProcessing());
        xml.append(super.elementProcessing("userName", this.userName, Boolean.TRUE));
        xml.append(super.elementProcessing("totalNumber", String.valueOf(this.totalNumber), Boolean.TRUE));
        xml.append(super.elementProcessing("totalAmount", this.totalAmount.toString(), Boolean.TRUE));
        xml.append("<list name=\"userDataList\">");

        userDataList.forEach(dlintfcsRequestUserData -> {
            xml.append(dlintfcsRequestUserData.processing());
        });

        xml.append("</list>");
        xml.append("</stream>");

        return xml.toString();
    }
}
