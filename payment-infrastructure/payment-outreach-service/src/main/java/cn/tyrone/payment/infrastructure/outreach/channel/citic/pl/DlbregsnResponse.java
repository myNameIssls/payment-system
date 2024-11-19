package cn.tyrone.payment.infrastructure.outreach.channel.citic.pl;

import cn.tyrone.payment.infrastructure.outreach.channel.citic.enums.CiticStatus;
import cn.tyrone.payment.sdk.util.XmlUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;

/**
 * 会员注册
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DlbregsnResponse extends AbstractCiticBaseResponse {

    /**
     * 资金分簿编号
     */
    private String subAccNo;
    /**
     * 资金分簿名称
     */
    private String subAccNm;
    /**
     * 客户号
     */
    private String hostNo;

    public DlbregsnResponse(String responseMessage) throws DocumentException {
        super(responseMessage);
        this.processing(this.document);
    }

    @Override
    protected void processing(Document document) throws RuntimeException {
        if (this.citicStatus.equals(CiticStatus.AAAAAAA)) {
            this.subAccNo = XmlUtil.getSingleNodeText(document, "//subAccNo");
            this.subAccNm = XmlUtil.getSingleNodeText(document, "//subAccNm");
            this.hostNo = XmlUtil.getSingleNodeText(document, "//hostNo");
        }
    }
}
