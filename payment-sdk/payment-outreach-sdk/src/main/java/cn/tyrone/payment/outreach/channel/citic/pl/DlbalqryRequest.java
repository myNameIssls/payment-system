package cn.tyrone.payment.outreach.channel.citic.pl;

import cn.tyrone.payment.outreach.channel.citic.enums.CiticAction;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 余额查询
 */
@Data
@SuperBuilder
public class DlbalqryRequest extends AbstractCiticBaseRequest {

    private List<DlbalqryRequestUserData> userDataList;

    @Override
    public String processing() throws RuntimeException {

        this.citicAction = CiticAction.DLBALQRY;

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
