package cn.tyrone.payment.channelctx.pl;

import lombok.Getter;

/**
 * 业务功能标识
 */
@Getter
public enum FunctionFlag {

    ONE("1", "开户"),
    TWO("2", "修改"),
    THREE("3", "销户"),
    ;

    private String fcFlg;

    private String describe;

    private FunctionFlag(String fcFlg, String describe){
        this.fcFlg = fcFlg;
        this.describe = describe;
    }




}
