package cn.tyrone.payment.channel.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 结算账户应答
 */
@Data
@SuperBuilder
@NoArgsConstructor
//@AllArgsConstructor
@ToString(callSuper = true)
public class SettlementAccountResponse extends CommonResponse {

}
