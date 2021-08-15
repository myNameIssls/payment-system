package cn.tyrone.payment.channel.common.entity;

import cn.tyrone.payment.channel.common.enums.ChannelConfigCode;
import cn.tyrone.payment.channel.common.enums.PaymentChannelConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommonRequest {

    /**
     * 平台流水号
     */
    private String platformSerialNumber;

    /**
     * 支付渠道配置信息
     */
    private PaymentChannelConfig paymentChannelConfig;

    public ChannelConfigCode getChannelConfigCode(){

        if (paymentChannelConfig == null) {
            throw new NullPointerException("支付渠道配置为空");
        }

        return this.paymentChannelConfig.getChannelConfigCode();
    }


}
