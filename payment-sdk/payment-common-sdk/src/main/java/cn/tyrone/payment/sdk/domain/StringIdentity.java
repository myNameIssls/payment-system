package cn.tyrone.payment.sdk.domain;

import cn.hutool.core.util.IdUtil;

public class StringIdentity implements Identity<String> {

    private final String identity;

    public StringIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String value() {

        String s = IdUtil.getSnowflake().nextIdStr();

        return this.identity;
    }

}
