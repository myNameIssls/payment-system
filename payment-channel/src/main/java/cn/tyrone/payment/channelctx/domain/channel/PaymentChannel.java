package cn.tyrone.payment.channelctx.domain.channel;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;

/**
 * 支付通道
 */
@Entity
@Table
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class PaymentChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(50) comment '支付通道名称'")
    private String name;

    /**
     * 支付通道唯一编码
     */
    @Convert(converter = PaymentChannelUniqueCodeConverter.class)
    @Column(name = "payment_channel_unique_code", nullable = false,  columnDefinition = "varchar(50) comment '支付通道唯一编码'" )
    private PaymentChannelUniqueCode paymentChannelUniqueCode;

    @Type(type = "json")
    @Column(name = "extend_info", nullable = true, columnDefinition = "json comment '支付通道配置'")
    private Map<String, Object> paymentChannelConfig;

}
