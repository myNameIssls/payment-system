package cn.tyrone.payment.channel.common.entity;

import cn.tyrone.payment.channel.common.enums.TransactionStatusQueryType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 交易状态查询请求
 */
@Data
@SuperBuilder
public class TransactionStatusQueryRequest extends CommonRequest {

    private TransactionStatusQueryType queryType;

}
