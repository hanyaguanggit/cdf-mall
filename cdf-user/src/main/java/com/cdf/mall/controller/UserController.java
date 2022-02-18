package com.cdf.mall.controller;

import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.commons.ResultCode;
import com.cdf.mall.module.UserModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 用户相关控制器
 * @Author hanyaguang
 * @Date 2022/2/17 14:24
 * @Version 1.0
 */
@RestController
@RequestMapping("/cdf/user")
public class UserController {

    @Autowired
    private UserModule userModule;

    /**
     * hyg
     * 后台--保存文章
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public CommonResult selectUser(@PathVariable Integer id) {
        CommonResult response ;
        try {
            response = userModule.selectUser(id);
        } catch (Exception e ) {
            return CommonResult.failed(ResultCode.FAILED.getCode(),e.getMessage());
        }
        return response;
    }
}
