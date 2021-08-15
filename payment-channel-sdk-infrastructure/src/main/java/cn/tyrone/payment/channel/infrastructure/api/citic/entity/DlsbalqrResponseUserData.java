package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dom4j.Node;

/**
 * 会员注册
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DlsbalqrResponseUserData extends AbstractCiticBaseResponse {

    /**
     * 资金分簿编号
     */
    private String subAccNo;
    /**
     * 资金分簿名称
     */
    private String subAccNm;

    /**
     * 透支额度
     */
    private String tzAmt;

    /**
     * 实体账户可用资金
     */
    private String xsacvl;

    /**
     * 可用余额
     */
    private String kyAmt;

    /**
     * 实际余额
     */
    private String sjAmt;

    /**
     * 冻结金额
     */
    private String djAmt;


    public void init(Node parentNode) {

        this.subAccNo = this.getChildNodeText(parentNode, "subAccNo");
        this.subAccNm = this.getChildNodeText(parentNode, "SUBACCNM");
        this.tzAmt = this.getChildNodeText(parentNode, "TZAMT");
        this.xsacvl = this.getChildNodeText(parentNode, "XSACVL");
        this.kyAmt = this.getChildNodeText(parentNode, "KYAMT");
        this.sjAmt = this.getChildNodeText(parentNode, "SJAMT");
        this.djAmt = this.getChildNodeText(parentNode, "DJAMT");

    }

}
