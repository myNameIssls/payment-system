package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

import cn.tyrone.payment.channel.infrastructure.api.citic.enums.CiticStatus;
import cn.tyrone.payment.channel.infrastructure.api.common.util.XmlUtil;
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
public class DlcidsttResponse extends AbstractCiticBaseResponse {

    /**
     * 客户流水号
     */
    private String clientID;

    /**
     * 状态结果集
     */
    private List<DlcidsttResponseUserData> userDataList;

    public DlcidsttResponse(String responseMessage) throws DocumentException {
        super(responseMessage);
        this.processing(this.document);
    }

    @Override
    protected void processing(Document document) throws RuntimeException {
        if (this.citicStatus.equals(CiticStatus.AAAAAAA)) {

            List<Node> parentNodeList = XmlUtil.getNodeList(document, "//stream/list/row");

            this.userDataList = parentNodeList.stream().map(node -> {
                DlcidsttResponseUserData userData = DlcidsttResponseUserData.builder().build();
                userData.init(node);
                return userData;
            }).collect(Collectors.toList());

        }
    }


}
