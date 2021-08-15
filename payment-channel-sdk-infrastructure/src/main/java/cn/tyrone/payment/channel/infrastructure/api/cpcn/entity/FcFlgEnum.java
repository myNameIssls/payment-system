package cn.tyrone.payment.channel.infrastructure.api.cpcn.entity;

import lombok.Getter;

/**
 * 业务功能标识
 */
@Getter
public enum FcFlgEnum {

    ONE("1", "开户"),
    TWO("2", "修改"),
    THREE("3", "销户"),
    ;

    private String fcFlg;

    private String describe;

    private FcFlgEnum(String fcFlg, String describe){
        this.fcFlg = fcFlg;
        this.describe = describe;
    }




}
