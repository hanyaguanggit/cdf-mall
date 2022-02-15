package com.cdf.mall.module;

import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.commons.ResultCode;
import com.cdf.mall.dto.SaveArticleReqVo;
import com.cdf.mall.model.CdfArticle;
import com.cdf.mall.model.CdfUser;
import com.cdf.mall.service.ArticleService;
import com.cdf.mall.service.UserService;
import com.cdf.mall.util.RedisOrderOpsUtil;
import com.cdf.mall.util.RedisUserOpsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ArticleModule.class);

    @Autowired
    private RedisOrderOpsUtil redisOrderTemplate;

    @Autowired
    private RedisUserOpsUtil redisUserTemplate;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;


    public CommonResult createArticle(SaveArticleReqVo request){
        CommonResult result = new CommonResult();
        CdfArticle article = new CdfArticle();
        StringBuilder sb = new StringBuilder("article_");

        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        article.setUserid(request.getUserId());
        articleService.insert(article);
        String articleKey = sb.append(article.getId()).toString();
       boolean setcache1 =  redisOrderTemplate.set(articleKey,article.toString());
       if(setcache1){
           logger.info("向6379端口redis添加成功。");
       }
        boolean setcache2 = redisUserTemplate.set(articleKey,article.toString());
        if(setcache2){
            logger.info("向6380端口redis添加成功。");
        }

        //给user添加数据
        CdfUser user = new CdfUser();
        user.setUsername("hanyaguang");
        int add = userService.insert(user);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("保存成功");
        result.setData(article.getId());
        return result;
    }
}
