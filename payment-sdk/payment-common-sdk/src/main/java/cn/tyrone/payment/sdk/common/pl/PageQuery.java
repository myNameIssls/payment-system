package cn.tyrone.payment.sdk.common.pl;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageQuery implements Query {

    private Long pageNo = 1L; // 页号

    private Long pageSize = 10L; // 页大小

}
