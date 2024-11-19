package cn.tyrone.payment.infrastructure.outreach.channel.citic.pl;

import cn.tyrone.payment.infrastructure.outreach.channel.citic.enums.CiticAction;
import cn.tyrone.payment.infrastructure.outreach.channel.citic.enums.IsCurrDay;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 电子回单下载请求
 */
@Data
@SuperBuilder
public class DledcdtdRequest extends AbstractCiticBaseRequest {

    /**
     * 银行号varchar (3)，可空
     */
    private String bankId = "";
    /**
     * 是否为T+0 日varchar (1)， 1：是；2：否
     */
    private IsCurrDay isCurrDay;
    /**
     * 账号
     */
    private String accNo;

    private List<DledcdtdRequestUserData> userDataList;

    @Override
    public String processing() throws RuntimeException {

        this.citicAction = CiticAction.DLEDCDTD;

        StringBuffer xml = new StringBuffer();
        xml.append(super.statementProcessing());
        xml.append("<stream>");
        xml.append(super.actionProcessing());
        xml.append(super.elementProcessing("userName", this.userName, Boolean.TRUE));
        xml.append(super.elementProcessing("bankId", this.bankId, Boolean.FALSE));
        xml.append(super.elementProcessing("isCurrDay", this.isCurrDay.getIsCurrDay(), Boolean.TRUE));
        xml.append(super.elementProcessing("accNo", this.accNo, Boolean.TRUE));
        xml.append("<list name=\"userDataList\">");
        userDataList.forEach(dledcdtdRequestUserData -> {
            xml.append("<row>");
            xml.append(dledcdtdRequestUserData.processing());
            xml.append("</row>");
        });
        xml.append("</list>");
        xml.append("</stream>");

        return xml.toString();
    }

}
