package cn.tyrone.payment.outreach.channel.citic.pl;

import cn.tyrone.payment.outreach.channel.citic.enums.CiticAction;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 支付转账请求
 */
@Data
@SuperBuilder
public class DlinttrnRequest extends AbstractCiticBaseRequest {

    private List<DlinttrnRequestUserData> userDataList;

    @Override
    public String processing() throws RuntimeException {

        this.citicAction = CiticAction.DLINTTRN;

        if (this.userDataList == null || this.userDataList.size() == 0) {
            throw new RuntimeException("支付转账明细记录不允许为空");
        }

        StringBuffer xml = new StringBuffer();
        xml.append(super.statementProcessing());
        xml.append("<stream>");
        xml.append(super.actionProcessing());
        xml.append(super.elementProcessing("userName", this.userName, Boolean.TRUE));
        xml.append("<list name=\"userDataList\">");
        userDataList.forEach(dlbalqryRequestUserData -> {
            xml.append("<row>");
            xml.append(dlbalqryRequestUserData.processing());
            xml.append("</row>");
        });
        xml.append("</list>");
        xml.append("</stream>");

        return xml.toString();
    }

}
