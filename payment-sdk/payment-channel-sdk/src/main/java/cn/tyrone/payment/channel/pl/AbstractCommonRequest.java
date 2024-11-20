package cn.tyrone.payment.channel.pl;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Map;

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

    /**
     * 支付通道配置
     */
    private Map<String, Object> paymentChannelConfig;


}
