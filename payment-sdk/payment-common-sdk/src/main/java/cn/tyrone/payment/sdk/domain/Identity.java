package cn.tyrone.payment.sdk.domain;

import java.io.Serializable;

public interface Identity<T> extends Serializable {

    T value();

}
