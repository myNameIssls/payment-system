package cn.tyrone.payment.channel.acl.adapter.route.cpcn.model;

import lombok.Getter;

/**
 *  发送端标记
 */
@Getter
public enum ReqFlgEnum {

    ONE("0", "手机"),
    TWO("1", "PC端"),
    ;

    private String fcFlg;

    private String describe;

    private ReqFlgEnum(String fcFlg, String describe){
        this.fcFlg = fcFlg;
        this.describe = describe;
    }

}
