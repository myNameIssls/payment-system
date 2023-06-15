package cn.tyrone.payment.channelctx.acl.adapter.route.citic.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 余额查询
 */
@Data
@SuperBuilder
public class DlsbalqrRequest extends AbstractCiticBaseRequest {

    private List<DlsbalqrRequestUserData> userDataList;

    @Override
    public String processing() throws RuntimeException {

        this.citicAction = CiticAction.DLBALQRY;

        StringBuffer xml = new StringBuffer();
        xml.append(super.statementProcessing());
        xml.append("<stream>");
        xml.append(super.actionProcessing());
        xml.append(super.elementProcessing("userName", this.userName, Boolean.TRUE));
        xml.append("<list name=\"userDataList\">");
        userDataList.forEach(dlsbalqrRequestUserData -> {
            xml.append("<row>");
            xml.append(dlsbalqrRequestUserData.processing());
            xml.append("</row>");
        });
        xml.append("</list>");
        xml.append("</stream>");

        return xml.toString();
    }

}
