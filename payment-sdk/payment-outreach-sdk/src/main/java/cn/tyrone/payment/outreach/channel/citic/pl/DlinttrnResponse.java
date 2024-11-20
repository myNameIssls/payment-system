package cn.tyrone.payment.outreach.channel.citic.pl;

import cn.tyrone.payment.outreach.channel.citic.enums.CiticStatus;
import cn.tyrone.payment.sdk.util.XmlUtil;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 支付转账应答
 */
@Data
@SuperBuilder
@ToString(callSuper = true)
public class DlinttrnResponse extends AbstractCiticBaseResponse {

    /**
     * 提交成功总笔数int
     */
    private int sucTotalNum;
    /**
     * 提交成功总金额decimal
     */
    private BigDecimal sucTotalAmt;
    /**
     * 提交失败总笔数
     */
    private int errTotalNum;
    /**
     * 提交失败总金额
     */
    private BigDecimal errTotalAmt;

    /**
     * 状态结果集
     */
    private List<DlinttrnResponseUserData> userDataList;

    public DlinttrnResponse(String responseMessage) throws DocumentException {
        super(responseMessage);
        this.processing(this.document);
    }

    @Override
    protected void processing(Document document) throws RuntimeException {
        if (this.citicStatus.equals(CiticStatus.AAAAAAA)) {

            this.sucTotalNum = Integer.parseInt(XmlUtil.getSingleNodeText(document, "//sucTotalNum"));
            this.sucTotalAmt = new BigDecimal(XmlUtil.getSingleNodeText(document, "//sucTotalAmt"));
            this.errTotalNum = Integer.parseInt(XmlUtil.getSingleNodeText(document, "//errTotalNum"));
            this.errTotalAmt = new BigDecimal(XmlUtil.getSingleNodeText(document, "//errTotalAmt"));

            List<Node> parentNodeList = XmlUtil.getNodeList(document, "//stream/list/row");

            this.userDataList = parentNodeList.stream().map(node -> {
                DlinttrnResponseUserData userData = DlinttrnResponseUserData.builder().build();
                userData.init(node);
                return userData;
            }).collect(Collectors.toList());

        }
    }


}
