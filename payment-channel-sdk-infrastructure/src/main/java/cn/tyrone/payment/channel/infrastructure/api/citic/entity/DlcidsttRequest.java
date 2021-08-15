package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 交易状态查询请求对象
 */
@Data
@SuperBuilder
public class DlcidsttRequest extends AbstractCiticBaseRequest {

    /**
     * 客户流水号
     */
    private String clientID;
    /**
     * 原请求代码
     */
    private String type;

    /**
     * 控制标签，当控制标签为0时，返回报文不返clientID，
     * 为1时返回报文返clientID，char(1)，标签可空
     */
    private ControlFlag controlFlag;

    @Override
    public String processing() throws RuntimeException {

        this.citicAction = CiticAction.DLCIDSTT;

        StringBuffer xml = new StringBuffer();
        xml.append(super.statementProcessing());
        xml.append("<stream>");
        xml.append(super.actionProcessing());
        xml.append(super.elementProcessing("userName", this.userName, Boolean.TRUE));
        xml.append(super.elementProcessing("clientID", this.clientID, Boolean.TRUE));
        xml.append(super.elementProcessing("type", this.type, Boolean.FALSE));
        xml.append(super.elementProcessing("controlFlag", ControlFlag.ONE_DLCIDSTT.getControlFlag(), Boolean.TRUE));
        xml.append("</stream>");

        return xml.toString();
    }
}
