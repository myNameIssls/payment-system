package cn.tyrone.payment.outreach.channel.citic.pl;

import cn.tyrone.payment.outreach.channel.citic.enums.BillType;
import cn.tyrone.payment.outreach.channel.citic.enums.SameBank;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DledcdtdRequestUserData extends AbstractCiticBaseRequest {

    /**
     * 回单编号
     */
    private String brSeqNo;
    /**
     * 回单类型varchar (6)，100000：存款回单；100001：取款回单；200000：转账回单；200001：缴税回单；300000：收费回单；400000定期回单；400001：活期回单
     */
    private BillType billType;


    @Override
    public String processing() throws RuntimeException {

        StringBuffer xml = new StringBuffer();

        xml.append(super.elementProcessing("brSeqNo", this.brSeqNo, Boolean.TRUE));
        xml.append(super.elementProcessing("billType", this.billType.getBillType(), Boolean.TRUE));


        return xml.toString();

    }

    public SameBank ifSameBank(String bankNumber) {
        return SameBank.ZERO;
    }

}
