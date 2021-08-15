package cn.tyrone.payment.channel.infrastructure.api.common.entity;

public class PaymentMessageObject {

    /**
     * 请求明文
     */
    private String requestPlaintext;

    /**
     * 请求密文
     */
    private String requestCiphertext;

    /**
     * 请求签名
     */
    private String requestSign;

    /**
     * 应答明文
     */
    private String responsePlaintext;

    /**
     * 应答密文
     */
    private String responseCiphertext;

    /**
     * 应答签名
     */
    private String responseSign;




}
