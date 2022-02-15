package com.cdf.mall.module;

import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.dto.SaveArticleReqVo;
import com.cdf.mall.util.RedisOrderOpsUtil;
import com.cdf.mall.util.RedisUserOpsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/2/14 17:44
 * @Version 1.0
 */
@Component
public class ArticleModule {

    @Autowired
    private RedisOrderOpsUtil redisOrderTemplate;

    @Autowired
    private RedisUserOpsUtil redisUserTemplate;


    public CommonResult createArticle(SaveArticleReqVo request){
        CommonResult result = new CommonResult();

        String key = "article";
        redisOrderTemplate.set(key,"123");
        redisUserTemplate.set(key,"345");
        return result;
    }
}
