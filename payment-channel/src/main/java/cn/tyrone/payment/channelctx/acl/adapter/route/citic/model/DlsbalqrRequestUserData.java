package cn.tyrone.payment.channelctx.acl.adapter.route.citic.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DlsbalqrRequestUserData extends AbstractCiticBaseRequest {

    /**
     * 付款账号
     */
    private String accountNo;


    @Override
    public String processing() throws RuntimeException {

        StringBuffer xml = new StringBuffer();

        xml.append(super.elementProcessing("accountNo", this.accountNo, Boolean.TRUE));


        return xml.toString();

    }

    public SameBank ifSameBank(String bankNumber) {
        return SameBank.ZERO;
    }

}
