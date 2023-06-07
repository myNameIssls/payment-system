package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.model;

import lombok.Getter;

/**
 * 业务标识
 */
@Getter
public enum TrsFlagEnum {

    NORMAL("A00", "正常入金"),
    NORMAL_TO_FREEZE("B00", "入金成功后，再冻结资金"),
    A00_T3004_T3005("A00", "普通订单支付"),
    B00_T3004_T3005("B00", " 收款方收款成功后，再冻结资金"),
    B01_T3004_T3005("B01", "付款方解冻资金后，再支付给收款方"),
    ;

    private String trsFlag;

    private String describe;

    private TrsFlagEnum(String fcFlg, String describe){
        this.trsFlag = fcFlg;
        this.describe = describe;
    }

}
