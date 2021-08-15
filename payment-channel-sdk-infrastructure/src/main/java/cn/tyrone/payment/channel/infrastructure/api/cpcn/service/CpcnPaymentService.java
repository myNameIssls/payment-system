package cn.tyrone.payment.channel.infrastructure.api.cpcn.service;

import com.trz.netwk.api.system.TrdMessenger;
import com.trz.netwk.api.trd.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
public class CpcnPaymentService {

    private String execute(TrdBaseRequest trdRequest){
        String result = null, response = null;

        try{

            trdRequest.process();
            log.debug("请求报文处理-交易码:{}-请求报文:{}", trdRequest.getMsghd_trcd(),trdRequest.getRequestPlainText());
            TrdMessenger trdMessenger = new TrdMessenger();
            result = trdMessenger.send(trdRequest);
            byte[] bytes = Base64.getDecoder().decode(result);
            response = new String(bytes);
            log.debug("响应报文处理-交易码:{}-响应报文:{}", trdRequest.getMsghd_trcd(),response);

        }catch(Exception e){
            log.error("中金支付-报文发送异常：", e);
        } finally {

        }
        return result;
    }

    /**
     * 开销户[T1001]
     * @param trdRequest
     * @return
     */
    public TrdT1001Response t1001(TrdT1001Request trdRequest) {

        TrdT1001Response trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT1001Response(response);
        } catch (Exception e) {
            log.error("开销户[T1001]-系统异常:",e);
        }

        return trdResponse;

    }

    /**
     * 结算账户维护[T1004]
     * @param trdRequest
     * @return
     */
    public TrdT1004Response t1004(TrdT1004Request trdRequest) {
        TrdT1004Response trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT1004Response(response);
        } catch (Exception e) {
            log.error("结算账户维护[T1004]系统异常:",e);
        }

        return trdResponse;
    }

    /**
     * 资金账户余额查询[T1005]
     * @param trdRequest
     * @return
     */
    public TrdT1005Response t1005(TrdT1005Request trdRequest) {
        TrdT1005Response trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT1005Response(response);
        } catch (Exception e) {
            log.error("资金账户余额查询[T1005]系统异常",e);
        }

        return trdResponse;
    }

    /**
     * 结算账户信息查询
     * @param trdRequest
     * @return
     */
    public TrdT1007Response t1007(TrdT1007Request trdRequest) {
        TrdT1007Response trdResponse = null;
        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT1007Response(response);
        } catch (Exception e) {
            log.error("结算账户信息查询[T1007]系统异常",e);
        }
        return trdResponse;
    }

    /**
     * 入金直通车_异步交易[T2031]
     * @param trdRequest
     * @return
     */
    public TrdT2031Response t2031(TrdT2031Request trdRequest) {
        TrdT2031Response trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT2031Response(response);
        } catch (Exception e) {
            log.error("入金直通车异步交易T2031-系统异常",e);
        }

        return trdResponse;
    }




    /**
     * 查询可代付出金额度[T1018]
     * @param trdRequest
     * @return
     */
    public TrdT1018Response t1018(TrdT1018Request trdRequest) {

        TrdT1018Response trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT1018Response(response);
        } catch (Exception e) {
            log.error("查询可代付出金额度[T1018]-系统异常",e);
        }

        return trdResponse;

    }

    /**
     * 出金-申请[T2022]
     * @param trdRequest
     * @return
     */
    public TrdCommonResponse t2022(TrdT2022Request trdRequest) {
        TrdCommonResponse trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdCommonResponse(response);
        } catch (Exception e) {
            log.error("出金-申请[T2022]-系统异常",e);
        }

        return trdResponse;
    }

    /**
     * 订单支付[T3004]
     * @param trdRequest
     * @return
     */
    public TrdT3004Response t3004(TrdT3004Request trdRequest) {
        TrdT3004Response trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT3004Response(response);
        } catch (Exception e) {
            log.error("订单支付[T3004]-系统异常",e);
        }

        return trdResponse;
    }

    /**
     * 批量订单支付[T3005]
     * @param trdRequest
     * @return
     */
    public TrdT3005Response t3005(TrdT3005Request trdRequest) {
        TrdT3005Response trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT3005Response(response);
        } catch (Exception e) {
            log.error("订单支付[T3004]-系统异常",e);
        }

        return trdResponse;
    }

    /**
     * 企业-企业账户认证(打款认证)-申请[T1131]
     * @param trdRequest
     * @return
     */
    public TrdCommonResponse t1131(TrdT1131Request trdRequest) {

        TrdCommonResponse trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdCommonResponse(response);
        } catch (Exception e) {
            log.error("企业-企业账户认证(打款认证)-申请[T1131]系统异常",e);
        }

        return trdResponse;

    }

    /**
     * 企业-企业账户认证(打款认证)-验证[T1132]
     * @param trdRequest
     * @return
     */
    public TrdT1132Response t1132(TrdT1132Request trdRequest) {
        TrdT1132Response trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT1132Response(response);
        } catch (Exception e) {
            log.error("企业-企业账户认证(打款认证)-验证[T1132]系统异常:",e);
        }

        return trdResponse;
    }

    /**
     * 查询往来账明细(当前日期)[T9007]
     * @param trdRequest
     * @return
     */
    public TrdT9007Response t9007(TrdT9007Request trdRequest) {

        TrdT9007Response trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT9007Response(response);
        } catch (Exception e) {
            log.error("查询往来账明细(当前日期)[T9007]系统异常:",e);
        }

        return trdResponse;

    }

    /**
     * 查询往来账明细(历史日期)[T9008]
     * @param trdRequest
     * @return
     */
    public TrdT9008Response t9008(TrdT9008Request trdRequest) {

        TrdT9008Response trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT9008Response(response);
        } catch (Exception e) {
            log.error("查询往来账明细(当前日期)[T9008]系统异常:",e);
        }

        return trdResponse;

    }

    /**
     * 支持银行列表查询[T1008]
     * @param trdRequest
     * @return
     */
    public TrdT1008Response t1008(TrdT1008Request trdRequest) {

        TrdT1008Response trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT1008Response(response);
        } catch (Exception e) {
            log.error("支持银行列表查询[T1008]系统异常:",e);
        }

        return trdResponse;

    }


    /**
     * 支付行信息模糊查询[T1017]
     * @param trdRequest
     * @return
     */
    public TrdT1017Response t1017(TrdT1017Request trdRequest) {

        TrdT1017Response trdResponse = null;

        String response = this.execute(trdRequest);

        try {
            trdResponse = new TrdT1017Response(response);
        } catch (Exception e) {
            log.error("支付行信息模糊查询[T1017]系统异常:",e);
        }

        return trdResponse;

    }


}
