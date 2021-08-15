package cn.tyrone.payment.channel.common.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public enum PayFlowDirection {

    IN("0", "入账"),
    OUT("1", "出账"),
    ;

    /**
     * 支付流向
     */
    private String direction;

    /**
     * 支付流向说明
     */
    private String describe;

    private static Map<String, PayFlowDirection> payFlowDirectionMap = new HashMap<>();

    static {
        payFlowDirectionMap = Arrays.stream(PayFlowDirection.values()).collect(
                Collectors.toMap(
                        PayFlowDirection::getDirection, payFlowDirection -> payFlowDirection
                )
        );
    }

    public static PayFlowDirection getPayFlowDirection(String direction) {
        return payFlowDirectionMap.get(direction);
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    PayFlowDirection(String direction, String describe) {
        this.direction = direction;
        this.describe = describe;
    }
}
