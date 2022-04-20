package com.cdf.mall.controller;

import com.cdf.mall.annontaion.SystemLog;
import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.commons.ResultCode;
import com.cdf.mall.dto.req.ArticleListReqVo;
import com.cdf.mall.dto.req.SaveArticleReqVo;
import com.cdf.mall.module.ArticleModule;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description 文章相关
 * @Author hanyaguang
 * @Date 2022/2/11 11:24
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/article")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleModule articleModule;


    /**
     * hyg
     * 后台--保存文章
     * @param request
     * @return
     */
    @PostMapping(value = "/save", consumes = "application/json")
    @SystemLog(module="文章模块",methods="保存文章",serviceClass = "articleServiceImpl",queryMethod="selectByPrimaryKey",parameterType="Integer")
    public CommonResult saveArticle(@Valid @RequestBody SaveArticleReqVo request, @RequestHeader("userId")Integer userId, Errors errors) {
        CommonResult response ;
        try {
            request.setUserId(userId);
            response = articleModule.createArticle(request);
        } catch (Exception e ) {
            return CommonResult.failed(ResultCode.FAILED.getCode(),e.getMessage());
        }
        return response;
    }


    /**
     * hyg
     * 后台--修改文章标题
     * @param
     * @return
     */
    @PostMapping(value = "/update")
    @SystemLog(module="文章模块",methods="修改文章",serviceClass = "articleServiceImpl",queryMethod="selectByPrimaryKey",parameterType="Integer")
    public CommonResult updateArticle( @RequestParam("id")Integer id, @RequestParam("title") String title) {
        CommonResult response ;
        try {
            response = articleModule.updateArticle(id,title);
        } catch (Exception e ) {
            return CommonResult.failed(ResultCode.FAILED.getCode(),e.getMessage());
        }
        return response;
    }


    /**
     * hyg
     * 后台--条件查询文章列表
     * @param request
     * @return
     */
    @Schema(description = "条件查询文章列表")
    @PostMapping(value = "/list", consumes = "application/json")
    public CommonResult selectArticleList(@RequestBody ArticleListReqVo request, Errors errors) {
        CommonResult response ;
        try {
            response = articleModule.selectArtileList(request);
        } catch (Exception e ) {
            return CommonResult.failed(ResultCode.FAILED.getCode(),e.getMessage());
        }
        return response;
    }

    /**
     * hyg
     *
     * @param
     * @return
     */
    @Schema(description = "保存地区")
    @PostMapping(value = "/add/region")
    public CommonResult addRegion() {
        CommonResult response ;
        try {
            response = articleModule.insertRegionToMongo();
        } catch (Exception e ) {
            return CommonResult.failed(ResultCode.FAILED.getCode(),e.getMessage());
        }
        return response;
    }
}
