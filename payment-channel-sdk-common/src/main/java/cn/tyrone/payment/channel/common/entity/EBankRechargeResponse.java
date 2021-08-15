package cn.tyrone.payment.channel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EBankRechargeResponse extends CommonResponse {

    private Map<String, Object> params;

//    public EBankRechargeResponse(Builder builder) {
//        super(builder);
//        this.params = builder.params;
//    }

//    public static class Builder extends CommonResponseBuilder<Builder> {
//
//        private Map<String, Object> params;
//
//        public Builder params(Map<String, Object> params) {
//            this.params = params;
//            return this;
//        }
//
//        public EBankRechargeResponse build() {
//            return new EBankRechargeResponse(this);
//        }
//
//    }

}
