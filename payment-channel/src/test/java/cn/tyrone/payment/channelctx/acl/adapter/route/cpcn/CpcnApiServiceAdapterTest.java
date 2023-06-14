package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn;

import cn.hutool.core.util.IdUtil;
import cn.tyrone.payment.channelctx.pl.*;
import com.trz.netwk.api.system.InitSystem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {
                CpcnApiService.class, CpcnApiMessageConverter.class,
                CpcnApiServiceAdapter.class
        })
class CpcnApiServiceAdapterTest {

    @Autowired
    private CpcnApiServiceAdapter apiServiceAdapter;

    @BeforeAll
    static void beforeAll() throws Exception {

        InitSystem.initialize("/Users/shanglishuai/temp/conf/zjconf");

    }

    @Test
    void openAccount() {

        Enterprise enterprise = Enterprise.builder()
                .name("西海数字科技有限公司").unifiedSocialCreditCode("911101054523819425")
                .unifiedSocialCreditCodeBeginDate("20000101").unifiedSocialCreditCodeEndDate("20300101")
                .email("service@xh.com").address("花果山水帘洞")
                .cityCode("101010").enterpriseScale(EnterpriseScale.THREE)
                .licenceCMII("J3110010971901").registeredCapital("500")
                .businessScope("装饰设计；销售建材、日用品；承办展览展示活动；企业策划；道路货物运输（不含危险货物）。")
                .unifiedSocialCreditCodeUrl("https://tangpiao-test.oss-cn-hangzhou.aliyuncs.com/c4a7c7a0369340bb844da883e95766c0/233835-158178111567da.jpg")
                .build();
        LegalPerson legalPerson = LegalPerson.builder()
                .name("孙行者").phoneNumber("18600897656")
                .certificateNum("110101199001016839")
                .certificateSignDateBegin("20000101").certificateSignDateEnd("20200101")
                .certificateFrontStorageAddr("https://tangpiao-test.oss-cn-hangzhou.aliyuncs.com/c4a7c7a0369340bb844da883e95766c0/233835-158178111567da.jpg")
                .certificateBackStorageAddr("https://tangpiao-test.oss-cn-hangzhou.aliyuncs.com/c4a7c7a0369340bb844da883e95766c0/233835-158178111567da.jpg")
                .build();
        Operator operator = Operator.builder()
                .name("唐三藏")
                .certificateNum("110101199001019095").phoneNumber("18600878798")
                .certificateSignDateBegin("20000101").certificateSignDateEnd("20200101")
                .certificateFrontStorageAddr("https://tangpiao-test.oss-cn-hangzhou.aliyuncs.com/c4a7c7a0369340bb844da883e95766c0/233835-158178111567da.jpg")
                .certificateBackStorageAddr("https://tangpiao-test.oss-cn-hangzhou.aliyuncs.com/c4a7c7a0369340bb844da883e95766c0/233835-158178111567da.jpg")
                .build();

        OpenAccountRequest openAccountRequest = OpenAccountRequest.builder()
                .platformSerialNumber(IdUtil.getSnowflakeNextIdStr())
                .customerCode(IdUtil.getSnowflakeNextIdStr())
                .bankCode("102").bankAccount("760203000610")
                .bankBranchCode("102100099996")
                .enterprise(enterprise).legalPerson(legalPerson).operator(operator)
                .build();
        OpenAccountResponse openAccountResponse = apiServiceAdapter.openAccount(openAccountRequest);
        log.debug("开户结果:{}", openAccountResponse.toString());

    }

    @Test
    void accountBalance() {
        AccountBalanceRequest accountBalanceRequest = AccountBalanceRequest.builder()
                .subAccount("2308711003921814").subAccountName("中金二期测试供应商六")
                .build();
        AccountBalanceResponse accountBalanceResponse = apiServiceAdapter.accountBalance(accountBalanceRequest);
        log.debug("账户余额查询结果：{}", accountBalanceResponse.toString());
    }

    public static void main(String[] args) {
        log.debug("-------------");
    }

}