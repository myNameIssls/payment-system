package cn.tyrone.payment.infrastructure.outreach.channel.citic.pl;

import cn.tyrone.payment.infrastructure.outreach.channel.citic.enums.BillType;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.dom4j.Node;

import java.util.Arrays;

@Data
@SuperBuilder
public class DledcdtdResponseUserData extends AbstractCiticBaseResponse {

    /**
     * 回单编号
     */
    private String brSeqNo;
    /**
     * 回单类型
     * 100000：存款回单；100001：取款回单；200000：转账回单；200001：缴税回单；300000：收费回单；400000定期回单；400001：活期回单
     */
    private BillType billType;
    /**
     * 回单PDF文件名称
     */
    private String pdfName;


    public void init(Node parentNode) {

        String billTypeValue = this.getChildNodeText(parentNode, "billType");
        String brSeqNo = this.getChildNodeText(parentNode, "brseqNo");
        String pdfName = this.getChildNodeText(parentNode, "pdfName");

        this.billType = Arrays.stream(BillType.values()).filter(billType -> billType.getBillType().equals(billTypeValue)).findFirst().get();
        this.brSeqNo = brSeqNo;
        this.pdfName = pdfName;

    }

}
