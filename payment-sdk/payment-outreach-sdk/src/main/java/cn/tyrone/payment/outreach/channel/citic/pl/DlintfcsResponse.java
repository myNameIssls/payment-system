package cn.tyrone.payment.outreach.channel.citic.pl;

import cn.tyrone.payment.outreach.channel.citic.enums.CiticStatus;
import cn.tyrone.payment.sdk.util.XmlUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 平台出金应答
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class DlintfcsResponse extends AbstractCiticBaseResponse {

    /**
     * 批次号
     */
    private String batNo;
    /**
     * 银行受理成功总笔数
     */
    private String sucTotalNum;
    /**
     * 银行受理成功总金额
     */
    private String sucTotalAmt;
    /**
     * 银行受理失败总笔数
     */
    private String errTotalNum;

    /**
     * 银行受理失败总金额
     */
    private String errTotalAmt;

    /**
     * 出金结果集
     */
    private List<DlintfcsResponseUserData> userDataList;

    public DlintfcsResponse(String responseMessage) throws DocumentException {
        super(responseMessage);
        this.processing(this.document);
    }

    @Override
    protected void processing(Document document) throws RuntimeException {
        if (this.citicStatus.equals(CiticStatus.AAAAAAA)) {

            this.batNo = XmlUtil.getSingleNodeText(document, "//batNo");
            this.sucTotalNum = XmlUtil.getSingleNodeText(document, "//sucTotalNum");
            this.sucTotalAmt = XmlUtil.getSingleNodeText(document, "//sucTotalAmt");
            this.errTotalNum = XmlUtil.getSingleNodeText(document, "//errTotalNum");
            this.errTotalAmt = XmlUtil.getSingleNodeText(document, "//errTotalAmt");

            List<Node> parentNodeList = XmlUtil.getNodeList(document, "//stream/list/row");

            this.userDataList = parentNodeList.stream().map(node -> {
                DlintfcsResponseUserData userData = DlintfcsResponseUserData.builder().build();
                userData.init(node);
                return userData;
            }).collect(Collectors.toList());

        }
    }

}
