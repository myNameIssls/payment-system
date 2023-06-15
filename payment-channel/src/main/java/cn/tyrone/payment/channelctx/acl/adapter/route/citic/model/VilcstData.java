package cn.tyrone.payment.channelctx.acl.adapter.route.citic.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 会员联系人数据
 */
@Data
@SuperBuilder
public class VilcstData extends AbstractCiticBaseRequest {

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 邮件地址
     */
    private String mailAddress;

    @Override
    public String processing() throws RuntimeException {

        StringBuffer xml = new StringBuffer();

        xml.append(super.elementProcessing("contactName", this.contactName, Boolean.TRUE));
        xml.append(super.elementProcessing("contactPhone", this.contactPhone, Boolean.TRUE));
        xml.append(super.elementProcessing("mailAddress", this.mailAddress, Boolean.TRUE));

        return xml.toString();
    }

    public String listProcessing(){
        StringBuffer xml = new StringBuffer();
        xml.append("<row>");
        xml.append(this.processing());
        xml.append("</row>");
        return xml.toString();
    }

}
