package com.cdf.mall.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : hyg
 * @Date : 2021/11/10
 * @Description : TODO
 **/
@Slf4j
public class EMayMsgCliet {

    private static final String appId = "9SDK-EMY-0999-JDRUP";

    private static final String secretKey = "6FF6B33E02B4D9CB";

    /*public static CommonResult sendSingleLoginCode(String mobile, String code)
            throws SDKParamsException {
        try {
            SmsSDKClient client = new SmsSDKClient(appId, secretKey);
            String content = String.format("【三亚国际免税城】您的验证码：%s，用于登录使用，为保证您的账户安全，请勿外泄。如需帮助请致电400-6990-6956", code);
            SmsSingleRequest request = new SmsSingleRequest(mobile, content, null, null, "");
            ResultModel<SmsResponse> result = client.sendSingleSms(request);
            log.info("*******************亿美返回短信发送结果************************:" + JSON.toJSONString(result));
            if ("SUCCESS".equals(result.getCode())) {
                System.out.println("请求成功");
                SmsResponse response = result.getResult();
                log.info("*******************sendSingleSms***************:" + response.toString());
                return CommonResult.success("短信发送成功");
            } else {
                log.info("*********************手机号短信发送失败{}" + result.getResult());
                log.info("*********************手机号" + mobile + "登录验证码发送失败");
                return CommonResult.failed(result.getCode(), EMayResultCode.getErrorMessage(result.getCode()));
            }
        } catch (Exception e) {
            log.error("发送短信信息模块出错,{}",e);
            return CommonResult.failed("短信发送失败");
        }
    }
*/
    public static void sendSingleLoginMsg(String mobile, String message) {

    }
}
