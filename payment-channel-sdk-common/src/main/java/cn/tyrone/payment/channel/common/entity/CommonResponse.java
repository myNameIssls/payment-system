package cn.tyrone.payment.channel.common.entity;

import cn.tyrone.payment.channel.common.enums.CommunicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {

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
    private CommunicationStatus communicationStatus;

    /**
     * 应答消息
     */
    private String responseMessage;

}
