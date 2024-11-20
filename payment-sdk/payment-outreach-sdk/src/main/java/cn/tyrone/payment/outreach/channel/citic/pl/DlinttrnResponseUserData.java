package cn.tyrone.payment.outreach.channel.citic.pl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.dom4j.Node;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class DlinttrnResponseUserData extends AbstractCiticBaseResponse {

    /**
     * 客户流水号
     */
    private String clientID;

    public void init(Node parentNode) {

        this.clientID = this.getChildNodeText(parentNode, "clientID");
        this.parseStatusProcessing(parentNode);

    }


}
