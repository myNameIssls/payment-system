package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.model;

import lombok.Getter;

/**
 *  发送端标记
 */
@Getter
public enum ActiFlag {

    ZERO("0", "正常，自动激活"),
    ONE("1", "企业主动打款激活"),
    TWO("2", "后台人工审核激活"),
    THREE("3", "短信验证激活"),
    FOUR("4", "企业被动打款激活"),
    FIVE("5", "异步网关页面激活"),
    ;

    private String fcFlg;

    private String describe;

    private ActiFlag(String fcFlg, String describe){
        this.fcFlg = fcFlg;
        this.describe = describe;
    }

}
