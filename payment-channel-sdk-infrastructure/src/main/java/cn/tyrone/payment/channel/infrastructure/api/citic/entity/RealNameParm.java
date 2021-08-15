package cn.tyrone.payment.channel.infrastructure.api.citic.entity;

/**
 * 实名制更换char(1) ，0：账户名与账号全不换；1：账户名与账号全换；2：换账户名；3：换账号；
 * appFlag为3时，必须为0
 */
public enum RealNameParm {

    ZERO("0", "账户名与账号全不换"),
    ONE("1", "账户名与账号全换"),
    TWO("2", "换账户名"),
    THREE("3", "换账号")
    ;

    /**
     * 操作
     */
    private String realNameParm;

    /**
     * 说明
     */
    private String describe;

    private RealNameParm(String realNameParm, String describe){
        this.realNameParm = realNameParm;
        this.describe = describe;
    }

    public String getRealNameParm() {
        return realNameParm;
    }

    public void setRealNameParm(String realNameParm) {
        this.realNameParm = realNameParm;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
