package cn.tyrone.payment.infrastructure.outreach.channel.citic.pl;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dom4j.DocumentException;

/**
 * 强制转账应答报文对象
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class DlmdetrnResponse extends AbstractCiticBaseResponse {

    public DlmdetrnResponse(String responseMessage) throws DocumentException {
        super(responseMessage);
        this.processing(this.document);
    }

}
