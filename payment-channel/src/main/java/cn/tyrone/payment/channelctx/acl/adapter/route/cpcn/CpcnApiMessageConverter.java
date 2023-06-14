package cn.tyrone.payment.channelctx.acl.adapter.route.cpcn;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.model.ActiFlag;
import cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.model.CltKd;
import cn.tyrone.payment.channelctx.acl.adapter.route.cpcn.model.CrdTp;
import cn.tyrone.payment.channelctx.pl.*;
import cn.tyrone.payment.commonctx.pl.CertificateType;
import cn.tyrone.payment.commonctx.pl.SecondaryAccountType;
import com.trz.netwk.api.trd.TrdT1005Request;
import com.trz.netwk.api.trd.TrdT1005Response;
import com.trz.netwk.api.trd.TrdT1031Request;
import com.trz.netwk.api.trd.TrdT1031Response;
import com.trz.netwk.api.vo.FleInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
public class CpcnApiMessageConverter {

    private static final String SUCCESS_CODE = "000000";

    public TrdT1031Request fromOpenAccountRequest(OpenAccountRequest openAccountRequest) {

        Enterprise enterprise = openAccountRequest.getEnterprise();
        LegalPerson legalPerson = openAccountRequest.getLegalPerson();
        Operator operator = openAccountRequest.getOperator();

        TrdT1031Request request = new TrdT1031Request();

        request.setMsghd_trdt(LocalDateTimeUtil.format(LocalDate.now(), DateTimeFormatter.BASIC_ISO_DATE));
        request.setSrl_ptnsrl(openAccountRequest.getPlatformSerialNumber());

        request.setFcflg(FunctionFlag.ONE.getFcFlg());
        request.setAcctp(SecondaryAccountType.ONE.secondaryAccountType);
        request.setCltacc_cltno(openAccountRequest.getCustomerCode());
        request.setCltacc_cltnm(enterprise.getName());
        request.setClt_nm(legalPerson.getName());
        request.setClt_kd(CltKd.ONE.cltKd);
        request.setClt_cdtp(CertificateType.A.certificateType); // 证件类型
        request.setClt_cdno(legalPerson.getCertificateNum());
        request.setClt_cdisdt(legalPerson.getCertificateSignDateBegin());
        request.setClt_cdexdt(legalPerson.getCertificateSignDateEnd());

        // 公司
        String unifiedSocialCreditCode = enterprise.getUnifiedSocialCreditCode();
        request.setClt_uscid(unifiedSocialCreditCode);
        request.setClt_uscisdt(enterprise.getUnifiedSocialCreditCodeBeginDate());
        request.setClt_uscexdt(enterprise.getUnifiedSocialCreditCodeEndDate());

        request.setClt_mobno(legalPerson.getPhoneNumber());
        request.setClt_email(enterprise.getEmail());
        request.setClt_addr(enterprise.getAddress());
        request.setBkacc_citycd(enterprise.getCityCode());
        request.setClt_scale(enterprise.getEnterpriseScale().enterpriseScale);
        request.setClt_basicacctno(enterprise.getLicenceCMII());
        request.setClt_authcapital(enterprise.getRegisteredCapital());
        request.setClt_busiscope(enterprise.getBusinessScope());

        // 经办人信息
        request.setOper_nm(operator.getName());
        request.setOper_cdno(operator.getCertificateNum());
        request.setOper_mobno(operator.getPhoneNumber());
        request.setOper_cdisdt(operator.getCertificateSignDateBegin());
        request.setOper_cdexdt(operator.getCertificateSignDateEnd());

        List<FleInfo> fleInfoList = new ArrayList<>();

        // 企业法人正面照Url
        String certificateFrontUrl = legalPerson.getCertificateFrontStorageAddr();
        String certificateFrontBase64 = this.file2Base64(certificateFrontUrl);
        FleInfo fleInfo = new FleInfo();
        fleInfo.setDtlno(IdUtil.getSnowflakeNextIdStr());
        fleInfo.setBsity("1101");
        fleInfo.setFletheme("法定代表人/自然人身份证正面");
        fleInfo.setFlety("11");
        fleInfo.setFlenm("1101.jpg");
        fleInfo.setFlecont(certificateFrontBase64);
        fleInfoList.add(fleInfo);

        // 企业法人反面照Url
        String certificateBackUrl = legalPerson.getCertificateBackStorageAddr();
        String certificateBackBase64 = this.file2Base64(certificateBackUrl);
        FleInfo fleInfo1102 = new FleInfo();
        fleInfo1102.setDtlno(IdUtil.getSnowflakeNextIdStr());
        fleInfo1102.setBsity("1102");
        fleInfo1102.setFletheme("法定代表人/自然人身份证反面");
        fleInfo1102.setFlety("11");
        fleInfo1102.setFlenm("1102.jpg");
        fleInfo1102.setFlecont(certificateBackBase64);
        fleInfoList.add(fleInfo1102);

        String certificateFrontUrl1 = operator.getCertificateFrontStorageAddr();
        String certificateFrontUrl1Base64 = this.file2Base64(certificateFrontUrl1);
        FleInfo fleInfo1103 = new FleInfo();
        fleInfo1103.setDtlno(IdUtil.getSnowflakeNextIdStr());
        fleInfo1103.setBsity("1103");
        fleInfo1103.setFletheme("法定代表人/自然人身份证正面");
        fleInfo1103.setFlety("11");
        fleInfo1103.setFlenm("1103.jpg");
        fleInfo1103.setFlecont(certificateFrontUrl1Base64);
        fleInfoList.add(fleInfo1103);

        String certificateBackUrl1 = operator.getCertificateBackStorageAddr();
        String certificateBackUrl1Base64 = this.file2Base64(certificateBackUrl1);
        FleInfo fleInfo1104 = new FleInfo();
        fleInfo1104.setDtlno(IdUtil.getSnowflakeNextIdStr());
        fleInfo1104.setBsity("1104");
        fleInfo1104.setFletheme("法定代表人/自然人身份证反面");
        fleInfo1104.setFlety("11");
        fleInfo1104.setFlenm("1104.jpg");
        fleInfo1104.setFlecont(certificateBackUrl1Base64);
        fleInfoList.add(fleInfo1104);

        String unifiedSocialCreditCodeUrl = enterprise.getUnifiedSocialCreditCodeUrl();
        String unifiedSocialCreditCodeUrlBase64 = this.file2Base64(unifiedSocialCreditCodeUrl);
        FleInfo fleInfo1201 = new FleInfo();
        fleInfo1201.setDtlno(IdUtil.getSnowflakeNextIdStr());
        fleInfo1201.setBsity("1201");
        fleInfo1201.setFletheme("法定代表人/自然人身份证反面");
        fleInfo1201.setFlety("11");
        fleInfo1201.setFlenm("1201.jpg");
        fleInfo1201.setFlecont(unifiedSocialCreditCodeUrlBase64);
        fleInfoList.add(fleInfo1201);

        request.setFleInfoList(fleInfoList);

        request.setBkacc_bkid(openAccountRequest.getBankCode());
        request.setBkacc_accno(openAccountRequest.getBankAccount());

        request.setBkacc_crdtp(CrdTp.A.crdTp);
        request.setBkacc_cdtp(CertificateType.G.certificateType);
        request.setBkacc_cdno(enterprise.getUnifiedSocialCreditCode());
        request.setBkacc_crsmk("2");
        request.setBkacc_openbkcd(openAccountRequest.getBankBranchCode());
        request.setActiflag(ActiFlag.FOUR.getFcFlg());

        return request;

    }

    /**
     * 文件转Base64
     * @param ossUrl
     * @return
     * @throws IOException
     */
    private String file2Base64(String ossUrl) {
        log.debug("影像资料文件地址：{}", ossUrl);
        try {
            URL url = new URL("https://tangpiao-test.oss-cn-hangzhou.aliyuncs.com/1a2cdf14649b4c898f3038b500215b92/222507-1579443907f03f.jpg");
            byte[] byteArray = IOUtils.toByteArray(url);
            String bufferString = Base64.getEncoder().encodeToString(byteArray);
            return bufferString;
        } catch (Exception e) {
            log.error("附件文件转换失败：", e);
            throw new RuntimeException("附件文件转换失败：", e);
        }

    }

    public OpenAccountResponse toOpenAccountResponse(TrdT1031Response trdT1031Response) {

        String msghdRspcode = trdT1031Response.getMsghd_rspcode();
        String msghdRspmsg = trdT1031Response.getMsghd_rspmsg();

        OpenAccountResponse openAccountResponse = null;
        if (!msghdRspcode.equals(SUCCESS_CODE)) {
            openAccountResponse = OpenAccountResponse.builder()
                    .responseStatus(Boolean.FALSE).responseMessage(msghdRspmsg)
                    .build();
        }

        if (msghdRspcode.equals(SUCCESS_CODE)) {
            String srlPtnsrl = trdT1031Response.getSrl_ptnsrl();
            String srlPlatsrl = trdT1031Response.getSrl_platsrl();
            String cltaccSubno = trdT1031Response.getCltacc_subno();
            String cltaccCltnm = trdT1031Response.getCltacc_cltnm();
            String cltaccCltno = trdT1031Response.getCltacc_cltno();

            openAccountResponse = OpenAccountResponse.builder()
                    .responseStatus(Boolean.TRUE).responseMessage(msghdRspmsg)
                    .platformSerialNumber(srlPtnsrl).channelSerialNumber(srlPlatsrl)
                    .subAccount(cltaccSubno).subAccountName(cltaccCltnm).customerCode(cltaccCltno)
                    .build();
        }

        return openAccountResponse;

    }

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://tangpiao-test.oss-cn-hangzhou.aliyuncs.com/1a2cdf14649b4c898f3038b500215b92/222507-1579443907f03f.jpg");
        byte[] byteArray = IOUtils.toByteArray(url);
        String bufferString = Base64.getEncoder().encodeToString(byteArray);
        System.out.printf(bufferString);
    }

    public TrdT1005Request fromAccountBalanceRequest(AccountBalanceRequest accountBalanceRequest) {

        TrdT1005Request trdRequest = new TrdT1005Request();
        trdRequest.setMsghd_trdt(LocalDateTimeUtil.format(LocalDate.now(), DateTimeFormatter.BASIC_ISO_DATE));
        trdRequest.setCltacc_subno(accountBalanceRequest.getSubAccount());
        trdRequest.setCltacc_cltnm(accountBalanceRequest.getSubAccountName());
        return trdRequest;
    }

    public AccountBalanceResponse toAccountBalanceResponse(TrdT1005Response trdT1005Response) {

        String msghdRspcode = trdT1005Response.getMsghd_rspcode();
        String msghdRspmsg = trdT1005Response.getMsghd_rspmsg();

        AccountBalanceResponse accountBalanceResponse = null;
        if (!msghdRspcode.equals(SUCCESS_CODE)) {
            accountBalanceResponse = AccountBalanceResponse.builder()
                    .responseStatus(Boolean.FALSE).responseMessage(msghdRspmsg)
                    .build();
        }

        if (msghdRspcode.equals(SUCCESS_CODE)) {
            String srlPtnsrl = trdT1005Response.getSrl_ptnsrl();
            String srlPlatsrl = trdT1005Response.getSrl_platsrl();
            long amtBalamt = trdT1005Response.getAmt_balamt();
            long amtUseamt = trdT1005Response.getAmt_useamt();
            long amtFrzamt = trdT1005Response.getAmt_frzamt();

            accountBalanceResponse = AccountBalanceResponse.builder()
                    .responseStatus(Boolean.TRUE).responseMessage(msghdRspmsg)
                    .platformSerialNumber(srlPtnsrl).channelSerialNumber(srlPlatsrl)
                    .accountBalance(new BigDecimal(amtBalamt)).usableAmount(new BigDecimal(amtUseamt))
                    .frozenAmount(new BigDecimal(amtFrzamt))
                    .build();
        }

        return accountBalanceResponse;

    }
}
