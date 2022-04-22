package com.cdf.mall.util.httpclient;

import lombok.Data;

@Data
class MessageWrapper {


    public static <T extends Body> String from(T body,Long channel_id) {

        Request request = new Request();
        request.setBiz_content(JsonUtil.toJson(body));
        request.setSign(null);
        request.setToken(null);
        if(channel_id!=null) {
        	request.setChannel_id(channel_id);
        }
        String format = JsonUtil.toJson(request);
        return format;
    }


}