package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 批量转账请求对象
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BatchTransferAccountRequest extends CommonRequest {

    private List<TransferAccountRequest> transferAccountRequests;

}
