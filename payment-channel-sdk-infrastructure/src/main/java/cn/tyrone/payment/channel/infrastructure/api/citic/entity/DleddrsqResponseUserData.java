package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

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
public class DleddrsqResponseUserData extends AbstractCiticBaseResponse {

    /**
     * 回单编号
     */
    private String brseqNo;
    /**
     * 回单类型
     */
    private String billType;
    /**
     * 交易发起方流水号
     */
    private String transeqNo;


    public void init(Node parentNode) {

        this.brseqNo = this.getChildNodeText(parentNode, "brseqNo");
        this.transeqNo = this.getChildNodeText(parentNode, "transeqNo");
        this.billType = this.getChildNodeText(parentNode, "billType");

    }


}
