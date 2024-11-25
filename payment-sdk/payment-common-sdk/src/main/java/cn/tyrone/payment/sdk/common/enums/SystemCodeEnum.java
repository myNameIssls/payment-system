package cn.tyrone.payment.sdk.common.enums;


/**
 * @author Final
 * @since 2023/10/4
 */
public enum SystemCodeEnum implements Code {

  // 正常
  SUCCESS("200000", "成功"),

  // 前端请求参数错误

  NO_AUTH("400001", "未授权"),
  INVALID_PARAM("400002", "参数错误"),
  NO_FOUND("400004", "未找到"),
  REPEAT_REQ("400005", "重复请求"),

  NO_AUTHENTICATION("400006", "未认证"),

  ERROR_AUTHENTICATION("400007", "错误的认证信息"),

  SENSITIVE_URL_ACCESS("400008", "敏感地址的访问"),

  CODE_HAS_PAST("400009", "请重新获取图形验证码"),
  CODE_CHECK_ERROR("400010", "图形验证码不正确"),


  MOBILE_CODE_HAS_PAST("400011", "请重新获取手机验证码"),
  MOBILE_CODE_CHECK_ERROR("400012", "手机验证码不正确"),

  // 系统错误及业务错误
  BIZ_ERROR("500000", "业务异常"),
  TIME_OUT_ERROR("500001", "客户端调用超时"),
  NETWORK_ERROR("500002", "网络异常"),
  SERVICE_NOT_FOUND("500003", "服务未发现"),
  SYS_ERROR("888888", "系统异常"),
  UNKNOWN_ERROR("999999", "未知异常"),
  ;

  private String code;

  private String message;

  SystemCodeEnum(String code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public String code() {
    return this.code;
  }

  @Override
  public String message() {
    return this.message;
  }
}
