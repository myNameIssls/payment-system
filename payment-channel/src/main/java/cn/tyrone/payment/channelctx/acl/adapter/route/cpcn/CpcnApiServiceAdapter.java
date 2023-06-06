package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn;

import cn.tyrone.payment.channelctx.pl.OpenAccountRequest;
import cn.tyrone.payment.channelctx.pl.OpenAccountResponse;
import com.trz.netwk.api.trd.TrdT1031Request;
import com.trz.netwk.api.trd.TrdT1031Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 中金支付API服务适配器
 */
@Service
public class CpcnApiServiceAdapter {

    @Autowired
    private CpcnApiService apiService;
    @Autowired
    private CpcnApiMessageConverter messageConverter;

    public OpenAccountResponse openAccount(OpenAccountRequest openAccountRequest){

        TrdT1031Request trdT1031Request = messageConverter.fromOpenAccountRequest(openAccountRequest);
        TrdT1031Response trdT1031Response = apiService.t1031(trdT1031Request);
        OpenAccountResponse openAccountResponse = messageConverter.toOpenAccountResponse(trdT1031Response);

        return openAccountResponse;
    }


}
