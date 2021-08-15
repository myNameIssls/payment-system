package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 银行支行信息
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BankBranchBranchDTO {

    private String cityId;

    private String bankId;

    private String bankBranchId;

    private String bankBranchName;

}
