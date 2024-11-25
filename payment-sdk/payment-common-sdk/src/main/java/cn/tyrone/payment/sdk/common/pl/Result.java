package cn.tyrone.payment.sdk.common.pl;

import cn.tyrone.payment.sdk.common.enums.Code;
import cn.tyrone.payment.sdk.common.enums.SystemCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 统一返回
 *
 * @author Final
 * @since 2023/10/4
 */
@Setter
@Getter
@NoArgsConstructor
public class Result<D> implements Serializable {

  private String gid; // 全局流水号

  private Long timestamp; // 时间戳

  private String code; // 返回码

  private String message; // 返回消息

  private D data; // 返回数据

  public Result(String code, String message, D data) {
    this.timestamp = System.currentTimeMillis();
    this.code = code;
    this.message = message;
    this.data = data;
  }

  @JsonIgnore
  public boolean isSuccess() {
    return SystemCodeEnum.SUCCESS.code().equals(code);
  }

  public static <T> Result<T> success() {
    return new Result<>(SystemCodeEnum.SUCCESS.code(), SystemCodeEnum.SUCCESS.message(), null);
  }

  public static <T> Result<T> success(T data) {
    return new Result<>(SystemCodeEnum.SUCCESS.code(), SystemCodeEnum.SUCCESS.message(), data);
  }

  public static <T> Result<T> success(T data, String message) {
    return new Result<>(SystemCodeEnum.SUCCESS.code(), message, data);
  }

  public static <T> Result<T> fail(String code, String message) {
    return new Result<>(code, message, null);
  }

  public static <T> Result<T> fail(Code code) {
    return new Result<>(code.code(), code.message(), null);
  }
}
