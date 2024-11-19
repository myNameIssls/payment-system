package cn.tyrone.payment.channelctx.acl.adapter.route.citic.model;

import cn.tyrone.payment.sdk.util.XmlUtil;
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
public class DlsbalqrResponse extends AbstractCiticBaseResponse {

    /**
     * 状态结果集
     */
    private List<DlsbalqrResponseUserData> userDataList;

    public DlsbalqrResponse(String responseMessage) throws DocumentException {
        super(responseMessage);
        this.processing(this.document);
    }

    @Override
    protected void processing(Document document) throws RuntimeException {
        if (this.citicStatus.equals(CiticStatus.AAAAAAA)) {

            List<Node> parentNodeList = XmlUtil.getNodeList(document, "//stream/list/row");

            this.userDataList = parentNodeList.stream().map(node -> {
                DlsbalqrResponseUserData userData = DlsbalqrResponseUserData.builder().build();
                userData.init(node);
                return userData;
            }).collect(Collectors.toList());

        }
    }


}
