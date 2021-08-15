package cn.tyrone.payment.channel.infrastructure.api.citic.service;


import cn.tyrone.payment.channel.common.enums.PaymentChannelConfig;
import cn.tyrone.payment.channel.infrastructure.api.citic.entity.*;
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
public class CiticPaymentService {

    /**
     * 中信银行发送报文过程
     *
     * @param requestMessage
     * @param paymentChannelConfig
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private String execute(String requestMessage, PaymentChannelConfig paymentChannelConfig) throws Exception {

        Map<String, Object> channelConfig = paymentChannelConfig.getChannelConfig();

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

    private String execute(AbstractCiticBaseRequest request) throws Exception {

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        Map<String, Object> channelConfig = paymentChannelConfig.getChannelConfig();

        String requestMessage = request.processing();

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

    /**
     * 3.2.5 会员注册
     *
     * @param request
     * @return
     */
    public DlbregsnResponse dlbregsn(DlbregsnRequest request) {

        DlbregsnResponse response = null;

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        String requestMessage = request.processing();
        try {

            String responseMessage = this.execute(requestMessage, paymentChannelConfig);

            response = new DlbregsnResponse(responseMessage);

        } catch (Exception e) {
            log.error("中信银行会员注册系统异常:", e);
        }

        return response;
    }


    public DlsbalqrResponse dlsbalqr(DlsbalqrRequest request) {

        DlsbalqrResponse response = null;

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        String requestMessage = request.processing();
        try {

            String responseMessage = this.execute(requestMessage, paymentChannelConfig);

            response = new DlsbalqrResponse(responseMessage);

        } catch (Exception e) {
            log.error("中信银行商户资金分簿余额查询:", e);
        }

        return response;

    }

    public DlmdetrnResponse dlmdetrn(DlmdetrnRequest request) {
        DlmdetrnResponse response = null;

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        String requestMessage = request.processing();
        try {

            String responseMessage = this.execute(requestMessage, paymentChannelConfig);

            response = new DlmdetrnResponse(responseMessage);

        } catch (Exception e) {
            log.error("中信银行商户资金分簿余额查询系统异常:", e);
        }

        return response;
    }

    /**
     * 3.43 平台出金
     * 商户可使用此接口完成会员交易资金资金分簿出金功能。
     * @param request
     * @return
     */
    public DlintfcsResponse dlintfcs(DlintfcsRequest request) {

        DlintfcsResponse response = null;

        PaymentChannelConfig paymentChannelConfig = request.getPaymentChannelConfig();
        String requestMessage = request.processing();
        try {

            String responseMessage = this.execute(requestMessage, paymentChannelConfig);

            response = new DlintfcsResponse(responseMessage);

        } catch (Exception e) {
            log.error("中信银行平台出金系统异常:", e);
        }

        return response;
    }

    /**
     * 现金管理系统余额查询
     * @param request
     * @return
     */
    public DlbalqryResponse dlbalqry(DlbalqryRequest request){
        DlbalqryResponse response = null;

        try {

            String responseMessage = this.execute(request);

            response = new DlbalqryResponse(responseMessage);

        } catch (Exception e) {
            log.error("现金管理系统余额查询系统异常:", e);
        }

        return response;
    }

    /**
     * 现金管理系统账户明细信息查询
     * @param request
     * @return
     */
    public DltrnallResponse dltrnall(DltrnallRequest request){
        DltrnallResponse response = null;

        try {

            String responseMessage = this.execute(request);

            response = new DltrnallResponse(responseMessage);

        } catch (Exception e) {
            log.error("现金管理系统账户明细信息查询系统异常:", e);
        }

        return response;
    }

    /**
     * 现金管理系统支付转账
     * @param request
     * @return
     */
    public DlinttrnResponse dlinttrn(DlinttrnRequest request){
        DlinttrnResponse response = null;

        try {

            String responseMessage = this.execute(request);

            response = new DlinttrnResponse(responseMessage);

        } catch (Exception e) {
            log.error("现金管理系统支付转账系统异常:", e);
        }

        return response;
    }

    /**
     * 现金管理系统交易状态查询
     * @param request
     * @return
     */
    public DlcidsttResponse dlcidstt(DlcidsttRequest request){
        DlcidsttResponse response = null;

        try {

            String responseMessage = this.execute(request);

            response = new DlcidsttResponse(responseMessage);

        } catch (Exception e) {
            log.error("现金管理系统交易状态查询系统异常:", e);
        }

        return response;
    }

    /**
     * 现金管理系统回单信息查询
     * @param request
     * @return
     */
    public DleddrsqResponse dleddrsq(DleddrsqRequest request){
        DleddrsqResponse response = null;

        try {

            String responseMessage = this.execute(request);

            response = new DleddrsqResponse(responseMessage);

        } catch (Exception e) {
            log.error("现金管理系统交易状态查询系统异常:", e);
        }

        return response;
    }

    /**
     * 现金管理系统回单下载
     * @param request
     * @return
     */
    public DledcdtdResponse dledcdtd(DledcdtdRequest request){
        DledcdtdResponse response = null;

        try {

            String responseMessage = this.execute(request);

            response = new DledcdtdResponse(responseMessage);

        } catch (Exception e) {
            log.error("现金管理系统交易状态查询系统异常:", e);
        }

        return response;
    }

}
