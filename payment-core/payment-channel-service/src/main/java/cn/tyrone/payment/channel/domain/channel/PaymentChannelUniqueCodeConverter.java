package cn.tyrone.payment.channel.domain.channel;


import javax.persistence.AttributeConverter;

/**
 * 支付渠道唯一编码转换器
 */
public class PaymentChannelUniqueCodeConverter implements AttributeConverter<PaymentChannelUniqueCode, String> {

    @Override
    public String convertToDatabaseColumn(PaymentChannelUniqueCode paymentChannelUniqueCode) {
        return paymentChannelUniqueCode.uniqueValue;
    }

    @Override
    public PaymentChannelUniqueCode convertToEntityAttribute(String uniqueValue) {
        return PaymentChannelUniqueCode.of(uniqueValue);
    }
}
