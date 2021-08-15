package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 电子回单查询应答
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ElecReceiptQueryResponse extends CommonResponse {

    /**
     * 总记录数
     */
    private int totalCount;

    /**
     * 回单查询记录
     */
    private List<ElecReceiptQuery> elecReceiptQueries;

}
