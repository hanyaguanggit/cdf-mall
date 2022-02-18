package com.cdf.mall.module;

import com.alibaba.fastjson.JSONObject;
import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.commons.ResultCode;
import com.cdf.mall.dto.mongo.primary.CsRegion;
import com.cdf.mall.dto.req.ArticleListReqVo;
import com.cdf.mall.dto.req.SaveArticleReqVo;
import com.cdf.mall.dto.resp.ArticleRespVo;
import com.cdf.mall.feign.user.UserFeignService;
import com.cdf.mall.model.master.CdfArticle;
import com.cdf.mall.model.master.CdfArticleExample;
import com.cdf.mall.model.second.CdfUser;
import com.cdf.mall.service.master.ArticleService;
import com.cdf.mall.service.second.UserService;
import com.cdf.mall.util.PrimaryMongoUtil;
import com.cdf.mall.util.RedisOrderOpsUtil;
import com.cdf.mall.util.RedisUserOpsUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.CORBA.COMM_FAILURE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 文章业务类
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

    @Autowired
    private UserFeignService userFeignService;
    /**
     * 创建文章
     * @param request
     * @return
     */
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

        createUser();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("保存成功");
        result.setData(article.getId());
        return result;
    }

    /**
     * description: 添加用户
     * @Param:
     * @Return:
     * @Author: hanyaguang
     * @Date: 2022/2/16 16:49
     */
    public CommonResult createUser(){
        CommonResult result = new CommonResult();
        //给user添加数据
        CdfUser user = new CdfUser();
        user.setUsername("hanyaguang2");
        int add = userService.insert(user);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("保存成功");
        result.setData(user.getId());
        return result;
    }

    public CommonResult selectArtileList(ArticleListReqVo request){
        CommonResult result = new CommonResult();
        List<ArticleRespVo> articleRespVoList = new ArrayList<>();
        CdfArticleExample cdfArticleExample = new CdfArticleExample();
        try {
            CdfArticleExample.Criteria criteria = cdfArticleExample.or();
            criteria.andTitleLike(request.getTitle());
            cdfArticleExample.setOrderByClause("id asc");
            List<CdfArticle> list = articleService.selectByExample(cdfArticleExample,request.getPageNum(),request.getPageSize());
            if(!CollectionUtils.isEmpty(list)){
             list.forEach(l->{
                 ArticleRespVo articleRespVo = new ArticleRespVo();
                 BeanUtils.copyProperties(l,articleRespVo);
                 articleRespVo.setUserId(l.getUserid());
                 if(l.getUserid() != null){
                    CommonResult userResult = userFeignService.selectById(l.getUserid());
                    if(userResult.getCode() == ResultCode.SUCCESS.getCode()){
                        ObjectMapper mapper = new ObjectMapper();
                        CdfUser cdfUser = mapper.convertValue(userResult.getData(), CdfUser.class);
                        articleRespVo.setUserName(cdfUser.getUsername());
                    }
                 }
                 articleRespVoList.add(articleRespVo);
             });
                result.setCode(ResultCode.SUCCESS.getCode());
                result.setMessage(ResultCode.SUCCESS.getMessage());
                result.setData(articleRespVoList);
            }else {
                result.setData(ResultCode.SUCCESS.getCode());
                result.setMessage("查无数据");
            }
        } catch (Exception e) {
            logger.error("查询文章信息异常，异常信息：{}",e.getMessage());
            e.printStackTrace();
        }
        return  result;
    }


    /**
     * description: 保存mongodb
     * @Param:
     * @Return:
     * @Author: hanyaguang
     * @Date: 2022/2/18 14:41
     */
    public CommonResult insertRegionToMongo(){
        CommonResult result = new CommonResult();
        CsRegion csRegion = new CsRegion();
        csRegion.setCode("123");
        csRegion.setName("广州");
        csRegion.setParentId("111");
        csRegion.setStatus("1");
        PrimaryMongoUtil.save(csRegion);
        return result;
    }
}
