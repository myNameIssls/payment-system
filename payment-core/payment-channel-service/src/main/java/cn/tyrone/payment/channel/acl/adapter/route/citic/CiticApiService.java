package cn.tyrone.payment.channel.acl.adapter.route.citic;


import cn.tyrone.payment.outreach.channel.citic.pl.DlsbalqrRequest;
import cn.tyrone.payment.outreach.channel.citic.pl.DlsbalqrResponse;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

@Slf4j
@Service
public class CiticApiService {

    /**
     * 中信银行发送报文过程
     *
     * @param requestMessage
     * @param channelConfig
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private String execute(String requestMessage, Map<String, Object> channelConfig) throws Exception {


        // 前置应用地址
        String preApplicaionUrl = String.valueOf(channelConfig.get("pre_applicaion_url"));

        URL sendUrl = new URL(preApplicaionUrl.trim());
        URLConnection connection = sendUrl.openConnection();

        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);
        connection.setDoOutput(true);

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "GBK");
        out.write(requestMessage);
        out.flush();
        out.close();

        // 一旦发送成功，用以下方法就可以得到服务器的回应：
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GBK");

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStreamReader);
        String responseMessage = document.asXML();

        return responseMessage;
    }

    public DlsbalqrResponse dlsbalqr(DlsbalqrRequest request) {

        DlsbalqrResponse response = null;

        String requestMessage = request.processing();
        try {

//            String responseMessage = this.execute(requestMessage, request.getChannelConfig());

//            response = new DlsbalqrResponse(responseMessage);

        } catch (Exception e) {
            log.error("中信银行商户资金分簿余额查询:", e);
        }

        return response;

    }

}
