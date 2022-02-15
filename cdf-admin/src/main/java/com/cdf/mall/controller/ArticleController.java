package com.cdf.mall.controller;

import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.commons.ResultCode;
import com.cdf.mall.dto.SaveArticleReqVo;
import com.cdf.mall.module.ArticleModule;
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
}
