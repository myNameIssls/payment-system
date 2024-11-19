package cn.tyrone.payment.sdk.util;

/**
 * 支付唯一号码工具
 */
public class PaymentUniqueNumberUtil {


    /**
     * 生成32位流水号
     * yyyyMMddHHMMSS + 18位随机数
     * @return
     */
    public static String unique32SerialNumber(){

        String localDateTime = DateUtil.getLocalDateTime(DateUtil.Pattern.NORM_DATETIME_CLOSE_PATTERN);
        String idString = IdGenerator.nextIdString();

        String uniqueNumber = localDateTime + idString;

        return uniqueNumber;
    }

    /**
     * 生成18位流水号
     * @return
     */
    public static String unique18SerialNumber(){
        String idString = IdGenerator.nextIdString();
        return idString;
    }


}
