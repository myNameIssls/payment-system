package cn.tyrone.payment.outreach.channel.citic.pl;


import cn.tyrone.payment.outreach.channel.citic.enums.CiticStatus;
import cn.tyrone.payment.sdk.util.XmlUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 3.1.2 账户明细信息查询
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class DltrnallResponse extends AbstractCiticBaseResponse {

    /**
     * 账户
     */
    private String accountNo;
    /**
     * 账户名称
     */
    private String accountName;
    /**
     * 开户行名称
     */
    private String openBankName;
    /**
     * 总记录条数
     */
    private int totalRecords;
    /**
     * 返回记录条数
     */
    private int returnRecords;

    /**
     * 账户信息集合
     */
    private List<DltrnallResponseUserData> userDataList;

    public DltrnallResponse(String responseMessage) throws DocumentException {
        super(responseMessage);
        this.processing(this.document);
    }

    @Override
    protected void processing(Document document) throws RuntimeException {
        if (this.citicStatus.equals(CiticStatus.AAAAAAA)) {
            this.accountNo = XmlUtil.getSingleNodeText(document, "//accountNo");
            this.accountName = XmlUtil.getSingleNodeText(document, "//accountName");
            this.openBankName = XmlUtil.getSingleNodeText(document, "//openBankName");
            this.totalRecords = Integer.parseInt(XmlUtil.getSingleNodeText(document, "//totalRecords"));
            this.returnRecords = Integer.parseInt(XmlUtil.getSingleNodeText(document, "//returnRecords"));

            List<Node> parentNodeList = XmlUtil.getNodeList(document, "//stream/list/row");
            this.userDataList = parentNodeList.stream().map(node -> {
                DltrnallResponseUserData userDataResponse = new DltrnallResponseUserData();
                userDataResponse.init(node);
                return userDataResponse;
            }).collect(Collectors.toList());

        }
    }
}
