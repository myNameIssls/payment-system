package cn.tyrone.payment.channel.common.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 交易参与方信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionParticipant implements Serializable {

    /**
     * 客户ID
     */
    private String customerId;

    /**
     * 运营方 ID
     */
    private String operatorId;

    /**
     * 账户（虚拟账户、共管账户、银行账户等）
     */
    private String account;

    /**
     * 户名（虚拟账户、共管账户、银行账户等）
     */
    private String accountName;

    /**
     * 银行城市编码
     * 用于判断是否同城
     */
    private String bankCityCode;

    /**
     * 银行支行行号
     * 用于判断是否同行
     */
    private String bankBranchCode;

}
