package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn;

import cn.tyrone.payment.channelctx.pl.OpenAccountRequest;
import cn.tyrone.payment.channelctx.pl.OpenAccountResponse;
import com.trz.netwk.api.trd.TrdT1031Request;
import com.trz.netwk.api.trd.TrdT1031Response;
import org.springframework.stereotype.Service;

@Service
public class CpcnApiMessageConverter {


    public TrdT1031Request fromOpenAccountRequest(OpenAccountRequest openAccountRequest) {

        TrdT1031Request trdT1031Request = new TrdT1031Request();

        return trdT1031Request;

    }

    public OpenAccountResponse toOpenAccountResponse(TrdT1031Response trdT1031Response) {

        return new OpenAccountResponse();

    }
}
