package cn.tyrone.payment.channel.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 批量转账应答
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class BatchTransferAccountResponse extends CommonResponse {

    private List<TransferAccountResponse> transferAccountResponses;

}
