package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.dom4j.Node;

import java.util.Arrays;

@Data
@SuperBuilder
@ToString(callSuper = true)
public class DlcidsttResponseUserData extends AbstractCiticBaseResponse {

    /**
     * 状态标志  char(1) 0 成功 1 失败 2未知 3审核拒绝 4 用户撤销
     */
    private Stt stt;


    public void init(Node parentNode) {

        String sttValue = this.getChildNodeText(parentNode, "stt");

        this.stt = Arrays.stream(Stt.values()).filter(stt -> stt.getStt().equals(sttValue)).findFirst().get();
        this.parseStatusProcessing(parentNode);
    }

}
