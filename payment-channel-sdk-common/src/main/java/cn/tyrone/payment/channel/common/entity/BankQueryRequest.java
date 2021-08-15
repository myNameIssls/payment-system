package cn.tyrone.payment.channel.common.entity;

import cn.tyrone.payment.channel.common.enums.BankQueryFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 银行信息查询请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BankQueryRequest extends CommonRequest {

    private BankQueryFlag bankQueryFlag;

}
