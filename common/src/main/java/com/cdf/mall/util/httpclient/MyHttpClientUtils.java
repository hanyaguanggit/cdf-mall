package com.cdf.mall.util.httpclient;

import com.alibaba.fastjson.JSONObject;
import com.cdf.mall.common.CommonResult;
import com.cdf.mall.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

@Component
public class MyHttpClientUtils {

	private static Logger logger = LoggerFactory.getLogger(MyHttpClientUtils.class);

	private static String PATH;

	@Value("${memberPurchasePath}")
	public static void setPATH(String PATH) {
		MyHttpClientUtils.PATH = PATH;
	}

	/**
	 * @description: 统一访问接口方法
	 * @author: zlg
	 * @param request 接口参数
	 * @param url 接口URL
	 * @return: CommonResult
	 * @throws Exception
	 */
	public static <T> CommonResult getForObject(T request, String url, boolean isToken, String token) throws IllegalAccessException {
		CommonResult result = new CommonResult();
		// 查询配置表token
		String resStr = RestClient.getForObject(PATH + url, request, isToken==true?getHeaders(token):null, String.class);
		if(StringUtils.isEmpty(resStr)) {
			return result;
		}
		if(request != null) {
			logger.info("入参：{}", request.toString());
		}
		JSONObject res = JSONObject.parseObject(resStr);
		if(ResultCode.SUCCESS.getCode() == res.getInteger("code") || 0 == res.getInteger("code")) {
			result.setData(res.get("data"));
			result.setCode(ResultCode.SUCCESS.getCode());
			result.setMessage(ResultCode.SUCCESS.getMessage());
		}else {
			String addttionMessage = "";
			if(res.getJSONObject("addition") != null){
				addttionMessage = res.getJSONObject("addition").getString("message");
			}
			result.setCode(ResultCode.FAILED.getCode());
			result.setMessage(res.getString("message") + "," + addttionMessage);

		}
		logger.info("出参：{}", res);
		return result;
	}

	/**
	 * @description: 封装请求头
	 * @author: zlg
	 * @return: HttpEntity
	 */
	public static HttpEntity getHeaders(String token) {
		String terminalId = "08"; //（终端ID）
		String stockId = "6868"; //（门店ID）
		String channelId = "wechat"; //（渠道ID）
		String gray = "wechat"; //（灰度ID）
//		String token = "B1GKI46ER1NAFQ3B0VRE4444B8"; //（身份token）
		HttpHeaders headers = new HttpHeaders();
		headers.add("terminalId", terminalId);
		headers.add("stockId", stockId);
		headers.add("channelId", channelId);
		headers.add("gray", gray);
		headers.add("token", token);
		return new HttpEntity(null, headers);
	}

	/**
	 * @description: 封装请求头
	 * @author: zlg
	 * @return: HttpEntity
	 */
	public static HttpEntity getHeaders(MultiValueMap postParameters, String token) {
		String terminalId = "08"; //（终端ID）
		String stockId = "6868"; //（门店ID）
		String channelId = "wechat"; //（渠道ID）
		String gray = "wechat"; //（灰度ID）
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
		headers.setContentType(type);
		headers.add("terminalId", terminalId);
		headers.add("stockId", stockId);
		headers.add("channelId", channelId);
		headers.add("gray", gray);
		headers.add("token", token);
		return new HttpEntity(postParameters, headers);
	}
}
