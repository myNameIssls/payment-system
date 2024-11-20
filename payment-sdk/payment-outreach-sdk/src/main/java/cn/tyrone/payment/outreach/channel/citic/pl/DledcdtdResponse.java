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
 * 电子回单下载应答
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class DledcdtdResponse extends AbstractCiticBaseResponse {

    /**
     * 回单汇总文件内容varchar(1048576)，需用base64解码后再进行zip解压缩
     */
    private String fileConTent;
    /**
     * 回单汇总文件名称
     */
    private String fileName;
    /**
     * 数组长度
     */
    private String size;


    /**
     * 出金结果集
     */
    private List<DledcdtdResponseUserData> userDataList;

    public DledcdtdResponse(String responseMessage) throws DocumentException {
        super(responseMessage);
        this.processing(this.document);
    }

    @Override
    protected void processing(Document document) throws RuntimeException {
        if (this.citicStatus.equals(CiticStatus.AAAAAAA)) {

            this.fileConTent = XmlUtil.getSingleNodeText(document, "//fileConTent");
            this.fileName = XmlUtil.getSingleNodeText(document, "//fileName");
            this.size = XmlUtil.getSingleNodeText(document, "//size");

            List<Node> parentNodeList = XmlUtil.getNodeList(document, "//stream/list/row");

            this.userDataList = parentNodeList.stream().map(node -> {
                DledcdtdResponseUserData userData = DledcdtdResponseUserData.builder().build();
                userData.init(node);
                return userData;
            }).collect(Collectors.toList());

        }
    }

}
