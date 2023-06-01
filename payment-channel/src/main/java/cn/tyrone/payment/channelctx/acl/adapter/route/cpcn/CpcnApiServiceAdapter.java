package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn;

import cn.tyrone.payment.channelctx.domain.OpenAccountRequest;
import cn.tyrone.payment.channelctx.domain.OpenAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 中金支付API服务适配器
 */
@Service
public class CpcnApiServiceAdapter {

    @Autowired
    private CpcnApiService apiService;

    public OpenAccountResponse openAccount(OpenAccountRequest request){
        return null;
    }


}
