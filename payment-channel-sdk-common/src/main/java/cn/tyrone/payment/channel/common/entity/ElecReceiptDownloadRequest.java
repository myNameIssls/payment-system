package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 电子回单查询请求
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ElecReceiptDownloadRequest extends CommonRequest {

    /**
     * 账户（虚拟账户、共管账户、银行账户等）
     */
    private String account;

    private List<ElecReceiptDownload> elecReceiptDownloads;

}
