package cn.tyrone.payment.channel.common.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 交易业务信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionBusiness implements Serializable {

    /**
     * 业务 ID
     */
    private String businessId;

//    /**
//     * 订单类型
//     */
//    private PaymentOrderType paymentOrderType;

    /**
     * 付款金额
     */
    private BigDecimal paymentAmount;

}
