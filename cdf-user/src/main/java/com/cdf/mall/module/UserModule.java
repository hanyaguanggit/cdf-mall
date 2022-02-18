package com.cdf.mall.module;

import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.commons.ResultCode;
import com.cdf.mall.model.second.CdfUser;
import com.cdf.mall.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description 用户业务类
 * @Author hanyaguang
 * @Date 2022/2/17 14:12
 * @Version 1.0
 */
@Component
public class UserModule {

    private static final Logger logger = LoggerFactory.getLogger(UserModule.class);
    @Autowired
    private UserService userService;

    /**
     * description: 查询用户信息
     * @Param:
     * @Return:
     * @Author: hanyaguang
     * @Date: 2022/2/17 14:21
     */
    public CommonResult selectUser(Integer userId){
     CommonResult result = new CommonResult();
        try {
            CdfUser cdfUser = userService.selectByPrimaryKey(userId);
            if(cdfUser != null){
                result.setCode(ResultCode.SUCCESS.getCode());
                result.setMessage(ResultCode.SUCCESS.getMessage());
                result.setData(cdfUser);
            }else {
                result.setCode(ResultCode.SUCCESS.getCode());
                result.setMessage("查无此人");
            }
        } catch (Exception e) {
            logger.error("查询用户信息异常，异常信息：{}",e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
