package cn.tyrone.payment.channel.infrastructure.api.citic.enums;

import java.util.Arrays;

/**
 * 中信银行操作类型
 */
public enum CiticStatus {

    AAAAAAA("AAAAAAA", "交易成功"),
    AAAAAAB("AAAAAAB", "经办成功待审核"),
    AAAAAAC("AAAAAAC", "预约支付成功"),
    AAAAAAE("AAAAAAE", "已提交银行处理，需稍后使用交易状态查询"),
    BBBBBBB("BBBBBBB", "批次处理部分成功"),
    CCCCCCC("CCCCCCC", "交易处理中"),
    UNKNOWN("UNKNOWN", "交易状态未知"),
    ERROR("other", "其它情况")
    ;

    /**
     * 操作
     */
    private String status;

    /**
     * 说明
     */
    private String statusText;

    CiticStatus(String status, String statusText){
        this.status = status;
        this.statusText = statusText;
    }

    public static CiticStatus getCiticStatus(String status) {
        CiticStatus[] citicStatuses = CiticStatus.values();
        return Arrays.stream(citicStatuses).filter(
                citicStatus -> citicStatus.getStatus().equals(status)
        ).findFirst().orElse(CiticStatus.ERROR);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }


    public static void main(String[] args) {
        String statuss = "AAAAAAAa";
        CiticStatus citicStatus = getCiticStatus(statuss);
        System.out.printf(citicStatus.getStatus() + ":" + citicStatus.getStatusText());
    }

}
