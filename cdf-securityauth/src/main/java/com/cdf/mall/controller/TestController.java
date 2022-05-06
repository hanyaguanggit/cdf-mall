package com.cdf.mall.controller;

import com.cdf.mall.common.CommonResult;
import com.cdf.mall.common.TokenInfo;
import com.cdf.mall.domain.SpringContextUtil;
import com.cdf.mall.dto.req.ReqSecurityUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/5/6 9:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/test")
public class TestController {

    @Autowired
    private SpringContextUtil springContextUtil;

    @PostMapping(value = "/test1")
    @ResponseBody
    public CommonResult Test1(@Valid @RequestBody ReqSecurityUserVo request, Errors errors) {

       ApplicationContext context = SpringContextUtil.getApplicationContext();
       Object o = context.getBean("cdfUserDetailService");
        System.out.println(o);
        return CommonResult.success("");
    }
}
