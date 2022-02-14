package com.cdf.mall.module;

import ch.qos.logback.classic.pattern.ClassNameOnlyAbbreviator;
import com.cdf.mall.common.api.CommonResult;
import com.cdf.mall.util.RedisOrderOpsUtil;
import com.cdf.mall.util.RedisUserOpsUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/2/14 17:44
 * @Version 1.0
 */
public class ArticleModule {

    @Autowired
    private RedisOrderOpsUtil redisOrderTemplate;

    @Autowired
    private RedisUserOpsUtil redisUserOpsUtil;


    public CommonResult createArticle(){
        CommonResult result = new CommonResult();

        return result;
    }
}
