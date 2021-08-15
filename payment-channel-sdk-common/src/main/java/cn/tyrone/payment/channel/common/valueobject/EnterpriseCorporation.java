package cn.tyrone.payment.channel.common.valueobject;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 企业法人
 */
@Data
@SuperBuilder
public class EnterpriseCorporation extends PersonCustomer implements Serializable {
}
