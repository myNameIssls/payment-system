package cn.tyrone.payment.outreach.channel.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dom4j.Document;
import org.dom4j.Node;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractXmlBaseResponse {

//    /**
//     * 应答明文
//     */
//    protected String responsePlaintext;
//
//    /**
//     * 应答密文
//     */
//    protected String responseCiphertext;
//
//    /**
//     * 应答签名
//     */
//    protected String responseSign;

    /**
     * 应答报文根节点
     */
    protected Document document;

    /**
     * 解析报文
     * @param document 报文文档对象
     * @throws RuntimeException
     */
    protected abstract void processing(Document document) throws RuntimeException;

    /**
     * 根据父节点获取指定子节点名称的节点值
     * @param parentNode
     * @param childNodeName
     * @return
     */
    protected abstract String getChildNodeText(Node parentNode, String childNodeName);



}
