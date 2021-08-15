package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * 电子回单查询请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ElecReceiptQueryRequest extends CommonRequest {

    /**
     * 账户（虚拟账户、共管账户、银行账户等）
     */
    private String account;

    /**
     * 回单类型
     */
    private String elecReceiptType;

    /**
     * 起始日期
     */
    private LocalDate startDate;

    /**
     * 终止日期
     */
    private LocalDate endDate;

    /**
     * 请求记录条数
     */
    private int pageSize;

    /**
     * 起始记录号
     */
    private int startNum;

}
