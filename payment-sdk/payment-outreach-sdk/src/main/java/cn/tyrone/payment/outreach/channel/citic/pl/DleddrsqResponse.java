package cn.tyrone.payment.outreach.channel.citic.pl;

import cn.tyrone.payment.outreach.channel.citic.enums.CiticStatus;
import cn.tyrone.payment.sdk.util.XmlUtil;
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
 * 回单信息查询应答
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class DleddrsqResponse extends AbstractCiticBaseResponse {

    /**
     * 总记录数
     */
    private String totalCount;

    /**
     * 出金结果集
     */
    private List<DleddrsqResponseUserData> userDataList;

    public DleddrsqResponse(String responseMessage) throws DocumentException {
        super(responseMessage);
        this.processing(this.document);
    }

    @Override
    protected void processing(Document document) throws RuntimeException {
        if (this.citicStatus.equals(CiticStatus.AAAAAAA)) {

            this.totalCount = XmlUtil.getSingleNodeText(document, "//totalCount");

            List<Node> parentNodeList = XmlUtil.getNodeList(document, "//stream/list/row");

            this.userDataList = parentNodeList.stream().map(node -> {
                DleddrsqResponseUserData userData = DleddrsqResponseUserData.builder().build();
                userData.init(node);
                return userData;
            }).collect(Collectors.toList());

        }
    }

}
