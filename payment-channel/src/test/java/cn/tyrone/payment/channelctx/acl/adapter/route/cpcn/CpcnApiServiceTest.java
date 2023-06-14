package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.model.*;
import cn.tyrone.payment.channelctx.pl.FunctionFlag;
import cn.tyrone.payment.commonctx.pl.CertificateType;
import cn.tyrone.payment.commonctx.pl.SecondaryAccountType;
import com.trz.netwk.api.system.InitSystem;
import com.trz.netwk.api.trd.TrdT1005Request;
import com.trz.netwk.api.trd.TrdT1005Response;
import com.trz.netwk.api.trd.TrdT1031Request;
import com.trz.netwk.api.trd.TrdT1031Response;
import com.trz.netwk.api.vo.FleInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {
                CpcnApiService.class
        })
class CpcnApiServiceTest {

    @Autowired
    private CpcnApiService apiService;

    @BeforeAll
    static void beforeAll() throws Exception {

        InitSystem.initialize("/Users/shanglishuai/temp/conf/zjconf");

    }

    @Test
    void t1031() throws IOException {

        TrdT1031Request request = new TrdT1031Request();

        request.setMsghd_trdt(LocalDateTimeUtil.format(LocalDate.now(), DateTimeFormatter.BASIC_ISO_DATE));
        request.setSrl_ptnsrl(IdUtil.getSnowflake().nextIdStr());

        request.setFcflg(FunctionFlag.ONE.getFcFlg());
        request.setAcctp(SecondaryAccountType.ONE.secondaryAccountType);
        request.setCltacc_cltno("15993808703");
        request.setCltacc_cltnm("白云数字科技");
        request.setClt_nm("阿布都海力力·纳斯尔");
        request.setClt_kd(CltKd.ONE.cltKd);
        request.setClt_cdtp(CertificateType.A.certificateType); // 证件类型
        request.setClt_cdno("130681198510282834");
        request.setClt_cdisdt("20220511");
        request.setClt_cdexdt("20280511");

        // 公司
        String unifiedSocialCreditCode = "91234105MA3TJ0Q54K";
        String uscid=unifiedSocialCreditCode.length()==15?unifiedSocialCreditCode+"000":unifiedSocialCreditCode;
        request.setClt_uscid(uscid);
        request.setClt_orgcd(uscid);
        request.setClt_bslic(uscid);
        request.setClt_swdjh(uscid);
        request.setClt_uscisdt("20220512");
        request.setClt_uscexdt("20280511");


        request.setClt_mobno("15993808703");
        request.setClt_email("123456@qq.com");
        request.setClt_addr("北京市丰台区长辛店射击场路园博园内欧洲园A座4-2");
        request.setBkacc_citycd("110100");
        request.setClt_scale("01");
        request.setClt_basicacctno("J6020090319301");
        request.setClt_authcapital("1000");
        request.setClt_busiscope("经营范围包括技术开发、技术服务、计算机软件技术开发、经济信息咨询；");

        request.setOper_nm("马林");
        request.setOper_cdno("130681198510282834");
        request.setOper_mobno("15993808703");
        request.setOper_cdisdt("20220512");
        request.setOper_cdexdt("20280512");

        List<FleInfo> fleInfoList = new ArrayList<>();

        File file = new File("/Users/shanglishuai/temp/conf/zjconf/2131916000578536/1101.jpg");
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        String bufferString = Base64.getEncoder().encodeToString(buffer);

        FleInfo fleInfo = new FleInfo();
        fleInfo.setDtlno(IdUtil.getSnowflake().nextIdStr());
        fleInfo.setBsity("1101");
        fleInfo.setFletheme("1101.jpg");
        fleInfo.setFlety("11");
        fleInfo.setFlenm("1101.jpg");
        fleInfo.setFlecont(bufferString);
        fleInfoList.add(fleInfo);

        FleInfo fleInfo1102 = new FleInfo();
        fleInfo1102.setDtlno(IdUtil.getSnowflake().nextIdStr());
        fleInfo1102.setBsity("1102");
        fleInfo1102.setFletheme("法定代表人/自然人身份证反面");
        fleInfo1102.setFlety("11");
        fleInfo1102.setFlenm("1102.jpg");
        fleInfo1102.setFlecont(bufferString);
        fleInfoList.add(fleInfo1102);

        FleInfo fleInfo1103 = new FleInfo();
        fleInfo1103.setDtlno(IdUtil.getSnowflake().nextIdStr());
        fleInfo1103.setBsity("1103");
        fleInfo1103.setFletheme("法定代表人/自然人身份证正面");
        fleInfo1103.setFlety("11");
        fleInfo1103.setFlenm("1103.jpg");
        fleInfo1103.setFlecont(bufferString);
        fleInfoList.add(fleInfo1103);

        FleInfo fleInfo1104 = new FleInfo();
        fleInfo1104.setDtlno(IdUtil.getSnowflake().nextIdStr());
        fleInfo1104.setBsity("1104");
        fleInfo1104.setFletheme("法定代表人/自然人身份证反面");
        fleInfo1104.setFlety("11");
        fleInfo1104.setFlenm("1104.jpg");
        fleInfo1104.setFlecont(bufferString);
        fleInfoList.add(fleInfo1104);

        FleInfo fleInfo1201 = new FleInfo();
        fleInfo1201.setDtlno(IdUtil.getSnowflake().nextIdStr());
        fleInfo1201.setBsity("1201");
        fleInfo1201.setFletheme("法定代表人/自然人身份证反面");
        fleInfo1201.setFlety("11");
        fleInfo1201.setFlenm("1201.jpg");
        fleInfo1201.setFlecont(bufferString);
        fleInfoList.add(fleInfo1201);

        request.setFleInfoList(fleInfoList);

        request.setBkacc_bkid("103");
        request.setBkacc_accno("6243020300098909876");

        request.setBkacc_crdtp(CrdTp.A.crdTp);
        request.setBkacc_cdtp(CertificateType.H.certificateType);
        request.setBkacc_cdno("911101159A001A3A0N");
        request.setBkacc_crsmk("2");
        request.setBkacc_openbkcd("103100000026");

        request.setActiflag("4");
        request.setSrl_ptnsrl(IdUtil.getSnowflake().nextIdStr());


        TrdT1031Response trdT1031Response = apiService.t1031(request);
        System.out.println(request.getRequestPlainText());
        System.out.println(trdT1031Response.getResponsePlainText());

    }

    @Test
    void t1005(){
        TrdT1005Request trdRequest = new TrdT1005Request();
        trdRequest.setMsghd_trdt(LocalDateTimeUtil.format(LocalDate.now(), DateTimeFormatter.BASIC_ISO_DATE));
        trdRequest.setCltacc_subno("2308711003921814");
        trdRequest.setCltacc_cltnm("中金二期测试供应商六");
        TrdT1005Response trdT1005Response = apiService.t1005(trdRequest);
        log.debug(trdRequest.getRequestPlainText());
        log.debug(trdT1005Response.getResponsePlainText());
    }

}