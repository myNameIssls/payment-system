package cn.tyrone.payment.sdk.common.domain;

import java.io.Serializable;

public interface Identity<T> extends Serializable {

    T value();

}
