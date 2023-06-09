package cn.tyrone.payment.channelctx.pl;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AbstractCommonRequest {

    /**
     * 平台流水号
     */
    private String platformSerialNumber;

    /**
     * 支付渠道唯一编码
     */
    private String paymentChannelUniqueCode;

}
