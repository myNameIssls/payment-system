package cn.tyrone.payment.channel.pl;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AbstractCommonResponse {

    /**
     * 平台流水号
     */
    private String platformSerialNumber;

    /**
     * 渠道流水号
     */
    private String channelSerialNumber;

    /**
     * 应答状态
     */
    private Boolean responseStatus;

    /**
     * 应答消息
     */
    private String responseMessage;


}
