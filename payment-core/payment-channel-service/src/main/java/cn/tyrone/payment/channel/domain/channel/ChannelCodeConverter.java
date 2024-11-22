package cn.tyrone.payment.channel.domain.channel;


import javax.persistence.AttributeConverter;

/**
 * 支付渠道唯一编码转换器
 */
public class ChannelCodeConverter implements AttributeConverter<ChannelCode, String> {

    @Override
    public String convertToDatabaseColumn(ChannelCode channelCode) {
        return channelCode.uniqueValue;
    }

    @Override
    public ChannelCode convertToEntityAttribute(String uniqueValue) {
        return ChannelCode.of(uniqueValue);
    }
}
