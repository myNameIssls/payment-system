package cn.tyrone.payment.channel.infrastructure.api.citic.entity;


import cn.tyrone.payment.channel.infrastructure.api.common.entity.AbstractBaseResponse;
import cn.tyrone.payment.channel.infrastructure.api.common.util.XmlUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;

/**
 * 中信银行应答基类
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractCiticBaseResponse extends AbstractBaseResponse {

    /**
     * 账户状态
     */
    protected String status;

    /**
     * 账户状态信息
     */
    protected String statusText;

    protected CiticStatus citicStatus;

    public AbstractCiticBaseResponse(String responseMessage) throws DocumentException {
        this.responsePlaintext = responseMessage;
        this.document = XmlUtil.createDocument(this.responsePlaintext);
        this.parseStatusProcessing(this.document);
    }

    @Override
    protected void processing(Document document) throws RuntimeException {

    }

    /**
     * 解析状态过程
     * @param document
     */
    protected void parseStatusProcessing(Document document){

        String status = XmlUtil.getSingleNodeText(document, "/stream/status");
        String statusText = XmlUtil.getSingleNodeText(document, "/stream/statusText");

        this.status = status;
        this.statusText = statusText;

        this.citicStatus = CiticStatus.AAAAAAA;
        if (!status.equals(CiticStatus.AAAAAAA.getStatus())) {
            this.citicStatus = CiticStatus.getCiticStatus(status);
            citicStatus.setStatusText(statusText);
        }

    }

    /**
     * 解析状态过程
     * @param parentNode
     */
    protected void parseStatusProcessing(Node parentNode) {

        this.citicStatus = CiticStatus.AAAAAAA;

        String status = this.getChildNodeText(parentNode, "status");
        String statusText = this.getChildNodeText(parentNode, "statusText");

        this.status = status;
        this.statusText = statusText;

        if (!this.citicStatus.getStatus().equals(status)) {
            this.citicStatus = CiticStatus.getCiticStatus(status);
            citicStatus.setStatusText(statusText);
        }

    }

    /**
     * 根据父节点获取指定子节点名称的节点值
     * @param parentNode
     * @param childNodeName
     * @return
     */
    @Override
    protected String getChildNodeText(Node parentNode, String childNodeName) {
        String childNodeText = XmlUtil.getChildNodeText(parentNode, childNodeName);
        return childNodeText;
    }
}
