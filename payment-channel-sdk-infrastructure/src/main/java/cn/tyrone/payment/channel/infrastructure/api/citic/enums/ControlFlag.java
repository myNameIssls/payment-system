package cn.tyrone.payment.channel.infrastructure.api.citic.enums;

/**
 * 控制标签char(1)，
 * 0：客户系统不兼容银行端新增返回字段
 * 1：客户系统兼容银行端新增返回字段，标签可空
 */
public enum ControlFlag {

    ZERO("0", "客户系统不兼容银行端新增返回字段"),
    ONE("1", "客户系统兼容银行端新增返回字段，标签可空"),

    ZERO_DLCIDSTT("0", "返回报文不返clientID"),
    ONE_DLCIDSTT("1", "为1时返回报文返clientID"),
    ;

    /**
     * 操作
     */
    private String controlFlag;

    /**
     * 说明
     */
    private String describe;

    private ControlFlag(String controlFlag, String describe){
        this.controlFlag = controlFlag;
        this.describe = describe;
    }

    public String getControlFlag() {
        return controlFlag;
    }

    public void setControlFlag(String controlFlag) {
        this.controlFlag = controlFlag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
