package cn.tyrone.payment.channelctx.acl.adapter.client.cpcn;

import com.trz.netwk.api.system.TrdMessenger;
import com.trz.netwk.api.trd.TrdBaseRequest;
import com.trz.netwk.api.trd.TrdT1031Request;
import com.trz.netwk.api.trd.TrdT1031Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
public class CpcnApiService {

    private String execute(TrdBaseRequest trdRequest){
        String result = null, response = null;
        List<String> skips = Arrays.asList("T1008", "T1017");
        try{

            trdRequest.process();
            log.debug("请求报文处理-交易码:{}-请求报文:{}", trdRequest.getMsghd_trcd(),trdRequest.getRequestPlainText());
            TrdMessenger trdMessenger = new TrdMessenger();
            result = trdMessenger.send(trdRequest);
            byte[] bytes = Base64.getDecoder().decode(result);
            response = new String(bytes);

            if (!skips.contains(trdRequest.getMsghd_trcd())) {
                log.debug("响应报文处理-交易码:{}-响应报文:{}", trdRequest.getMsghd_trcd(),response);
            }

        }catch(Exception e){
            log.error("中金支付-报文发送异常：", e);
        } finally {

        }
        return result;
    }

    /**
     * 开户绑卡_异步[T1031]
     *
     * @param trdRequest
     * @return
     */
    public TrdT1031Response t1031(TrdT1031Request trdRequest) {
        TrdT1031Response trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT1031Response(response);
        } catch (Exception e) {
            log.error("开户绑卡_异步[T1031]系统异常:", e);
        }

        return trdResponse;
    }


}
