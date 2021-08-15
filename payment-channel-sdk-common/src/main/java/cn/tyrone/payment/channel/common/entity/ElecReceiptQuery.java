package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 电子回单查询应答
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ElecReceiptQuery {

    /**
     * 回单编号
     */
    private String elecReceiptNum;

    /**
     * 回单类型
     */
    private String elecReceiptType;

}
