package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

import cn.tyrone.payment.channel.infrastructure.api.citic.enums.CiticStatus;
import cn.tyrone.payment.channel.infrastructure.api.common.util.XmlUtil;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 3.42非登录打印明细查询
 */
@Data
@SuperBuilder
public class DlptdtqyResponse extends AbstractCiticBaseResponse {

    /**
     * 电子回单打印校验码集
     */
    private List<DlptdtqyResponseUserData> userDataList;

    public DlptdtqyResponse(String responseMessage) throws DocumentException {
        super(responseMessage);
        this.processing(this.document);
    }

    @Override
    protected void processing(Document document) throws RuntimeException {
        if (this.citicStatus.equals(CiticStatus.AAAAAAA)) {

            List<Node> parentNodeList = XmlUtil.getNodeList(document, "//stream/list/row");

            this.userDataList = parentNodeList.stream().map(node -> {
                DlptdtqyResponseUserData userData = DlptdtqyResponseUserData.builder().build();
                userData.init(node);
                return userData;
            }).collect(Collectors.toList());

        }
    }

}
