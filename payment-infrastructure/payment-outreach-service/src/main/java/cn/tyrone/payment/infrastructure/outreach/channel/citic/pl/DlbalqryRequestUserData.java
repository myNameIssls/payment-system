package cn.tyrone.payment.infrastructure.outreach.channel.citic.pl;

import cn.tyrone.payment.infrastructure.outreach.channel.citic.enums.SameBank;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DlbalqryRequestUserData extends AbstractCiticBaseRequest {

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
