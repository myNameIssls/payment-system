package cn.tyrone.payment.sdk.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import java.util.List;

public class XmlUtil {

    /**
     * 创建文档对象
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Document createDocument(String xml) throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        return document;
    }

    /**
     * 获取单节点
     * @param document
     * @param predicate
     * @return
     */
    public static Node getSingleNode(Document document, String predicate){
        Node node = document.selectSingleNode(predicate);
        return node;
    }

    /**
     * 获取单节点元素值
     * @param document
     * @param predicate
     * @return
     */
    public static String getSingleNodeText(Document document, String predicate){
        Node node = document.selectSingleNode(predicate);
        String text = node.getText();
        return text;
    }

    /**
     * 获取循环节点
     * @param document
     * @param predicate
     * @return
     */
    public static List<Node> getNodeList(Document document, String predicate) {

        List<Node> nodeList = document.selectNodes(predicate);

        return nodeList;
    }

    /**
     * 根据父节点获取指定名称的子节点
     * @param parentNode
     * @param childNodeName
     * @return
     */
    public static Node getChildNode(Node parentNode, String childNodeName) {
        List<Node> childNodes = parentNode.selectNodes("./*");
        Node childNode = childNodes.stream().filter(node -> node.getName().equals(childNodeName)).findFirst().get();
        return childNode;
    }

    /**
     * 根据父节点获取指定名称的子节点值
     * @param parentNode
     * @param childNodeName
     * @return
     */
    public static String getChildNodeText(Node parentNode, String childNodeName) {
        Node childNode = getChildNode(parentNode, childNodeName);
        String childNodeText = childNode.getText();
        return childNodeText;
    }

}
