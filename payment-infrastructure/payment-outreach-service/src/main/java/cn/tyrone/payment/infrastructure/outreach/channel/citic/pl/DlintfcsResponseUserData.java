package cn.tyrone.payment.infrastructure.outreach.channel.citic.pl;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.dom4j.Node;

/**
 * 平台出金应答
 */
@Data
@SuperBuilder
public class DlintfcsResponseUserData extends AbstractCiticBaseResponse {

    /**
     * 客户流水号
     */
    private String clientID;

    public void init(Node parentNode) {

        this.clientID = this.getChildNodeText(parentNode, "clientID");
        this.parseStatusProcessing(parentNode);

    }


}
