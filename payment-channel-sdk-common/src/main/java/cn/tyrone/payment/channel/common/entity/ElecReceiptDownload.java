package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 电子回单查询请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ElecReceiptDownload {

    /**
     * 回单编号
     */
    private String elecReceiptNum;

    /**
     * 回单类型
     */
    private String elecReceiptType;

    /**
     * 回单名称
     */
    private String elecReceiptName;

}
