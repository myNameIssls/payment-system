package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

import cn.tyrone.payment.channel.infrastructure.api.citic.enums.CiticStatus;
import cn.tyrone.payment.channel.infrastructure.api.common.util.XmlUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 会员注册
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DlsbalqrResponse extends AbstractCiticBaseResponse {

    /**
     * 账户信息集合
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
                DlsbalqrResponseUserData userDataResponse = new DlsbalqrResponseUserData();
                userDataResponse.init(node);
                return userDataResponse;
            }).collect(Collectors.toList());

        }
    }
}
