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
public class ElecReceiptDownloadResponse extends CommonResponse {

    /**
     * 文件内容
     */
    private String fileContent;

    /**
     * 回单汇总文件名称
     */
    private String fileName;

    private int size;

    private List<ElecReceiptDownload> elecReceiptDownloads;

}
