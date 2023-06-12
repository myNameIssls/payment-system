package cn.tyrone.payment.channelctx.pl;

import cn.tyrone.payment.commonctx.util.ToStringStyleExtend;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Data
@SuperBuilder
public class OpenAccountResponse extends AbstractCommonResponse {

    /**
     * 客户编码
     */
    private String customerCode;

    /**
     * 账户
     */
    private String subAccount;

    /**
     * 账户名称
     */
    private String subAccountName;

    /**
     * 银行电子账号
     */
    private String bankElecAccount;

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyleExtend.JSON_STYLE_EXTEND);

    }
}
