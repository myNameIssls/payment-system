package cn.tyrone.payment.channelctx.acl.adapter.route.citic;

import cn.tyrone.payment.commonctx.util.XmlUtil;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 交易状态查询应答
 */
@Data
@SuperBuilder
@ToString(callSuper = true)
public class DlbalqryResponse extends AbstractCiticBaseResponse {

    /**
     * 状态结果集
     */
    private List<DlbalqryResponseUserData> userDataList;

    public DlbalqryResponse(String responseMessage) throws DocumentException {
        super(responseMessage);
        this.processing(this.document);
    }

    @Override
    protected void processing(Document document) throws RuntimeException {
        if (this.citicStatus.equals(CiticStatus.AAAAAAA)) {

            List<Node> parentNodeList = XmlUtil.getNodeList(document, "//stream/list/row");

            this.userDataList = parentNodeList.stream().map(node -> {
                DlbalqryResponseUserData userData = DlbalqryResponseUserData.builder().build();
                userData.init(node);
                return userData;
            }).collect(Collectors.toList());

        }
    }


}
