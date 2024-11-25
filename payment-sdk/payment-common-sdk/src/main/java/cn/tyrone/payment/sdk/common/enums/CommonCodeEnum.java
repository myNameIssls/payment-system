package cn.tyrone.payment.sdk.common.enums;


/**
 * @author sushiran@iciyun.com 2023/10/11 11:51
 */
public enum CommonCodeEnum implements Code {
  REQUEST_ERROR("500001", "请求异常"),
  NOT_SUPPORTED("500002", "不支持"),
  NOT_ACTIVE("500003", "未生效"),
  DUPLICATE_KEY("500004", "重复数据"),
  NOT_AVAILABLE("500005", "服务繁忙"),
  ;

  /** 枚举值 */
  private final String code;

  /** 枚举描述 */
  private final String message;

  /**
   * @param code 枚举值
   * @param message 枚举描述
   */
  private CommonCodeEnum(String code, String message) {
    this.code = code;
    this.message = message;
  }

  /**
   * @return Returns the code.
   */
  public String code() {
    return code;
  }

  /**
   * @return Returns the message.
   */
  public String message() {
    return message;
  }
}
