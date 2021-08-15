package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 银行信息
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BankChannelDTO {

    private String bankId;

    private String bankName;

}
