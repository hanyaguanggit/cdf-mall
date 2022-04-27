package com.cdf.mall.module;


import com.cdf.mall.common.TokenInfo;
import com.cdf.mall.commons.CommonResult;
import com.cdf.mall.dto.req.ReqSecurityUserVo;
import com.cdf.mall.entity.CsSecurityUser;
import com.cdf.mall.service.CsSecurityUserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * hyg 2021.07.16
 * 后台管理员相关
 */
@Component
public class CsSecurityUserModule {
    Logger logger = LoggerFactory.getLogger(CsSecurityUserModule.class);

    @Autowired
    private CsSecurityUserService csSecurityUserService;


    /**
     * hyg
     * 生成验证码
     */
    /*public CommonResult generateAuthCode(String telephone){
       return csSecurityUserService.generateAuthCode(telephone);
    }*/

    /**
     * hyg
     * 登录后获取token
     */
    public TokenInfo login(String username, String password){
        return csSecurityUserService.login(username,password);
    }
    /**
     * ygl
     * 刷新token
     */
    public String refreshToken(String token){
        return csSecurityUserService.refreshToken(token);

    }

   /* public CommonResult<CsSecurityUser> loadUserByUsername(ReqSecurityUserVo request){
        CsSecurityUserExample csSecurityUserExample = new CsSecurityUserExample();
        CommonResult<CsSecurityUser> response = new CommonResult<>();
        CsSecurityUser csSecurityUser=null;

        try {
            //BeanUtils.copyProperties(request, csSecurityUserExample);
            csSecurityUserExample.createCriteria().andLoginnameEqualTo(request.getLoginName());
            csSecurityUser = csSecurityUserService.loadUserByUsername(csSecurityUserExample);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询后端认证会员信息异常 ",e.getMessage());
            response.setCode(ResultUserCode.FAILED.getCode());
            response.setMessage(ResultUserCode.FAILED.getMessage());
            return response;
        }
        if(ObjectUtils.isEmpty(csSecurityUser)){
            logger.info("查询后端认证会员信息空值",csSecurityUser);
            response.setCode(ResultUserCode.FAILED.getCode());
            response.setMessage(ResultUserCode.FAILED.getMessage());
            return response;
        }
        response.setData(csSecurityUser);
        response.setMessage(ResultUserCode.SUCCESS.getMessage());
        response.setCode(ResultUserCode.SUCCESS.getCode());
        return response;
    }*/

   /* *//**
     * description: 登录前，校验系统用户
     * @Param:
     * @Return:
     * @Author: hanyaguang
     * @Date: 2022/1/20 14:33
     *//*
    public CommonResult validSecurityUser(ReqSecurityUserVo request){
        CommonResult response = new CommonResult<>();
        try {
            CsSecurityUserExample csSecurityUserExample = new CsSecurityUserExample();
            CsSecurityUserExample.Criteria criteria = csSecurityUserExample.or();
            criteria.andLoginnameEqualTo(request.getLoginName());
            criteria.andPasswordEqualTo(AesUtils.encrypt("",request.getPassword()));
            CsSecurityUser csSecurityUser = csSecurityUserService.loadUserByUsername(csSecurityUserExample);
            if(!ObjectUtils.isEmpty(csSecurityUser)){
                //启用禁用验证 false 0禁用，true 1启用
                if(csSecurityUser.getEnabled() == false){
                    response.setCode(ResultAdminCode.ADMIN_USER_FORBIDDEN_ERROR.getCode());
                    response.setMessage(ResultAdminCode.ADMIN_USER_FORBIDDEN_ERROR.getMessage());
                    response.setData(false);
                }else {
                    response.setCode(ResultUserCode.SUCCESS.getCode());
                    response.setMessage(ResultUserCode.SUCCESS.getMessage());
                    response.setData(true);
                }
            }else {
                response.setCode(ResultUserCode.FAILED.getCode());
                response.setMessage("用户名或密码错误！");
                response.setData(false);
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.info("查询后端系统用户信息异常 ",e.getMessage());
            response.setCode(ResultUserCode.FAILED.getCode());
            response.setMessage(ResultUserCode.FAILED.getMessage());
            response.setData(false);
        }
        return response;
    }*/

 /*   *//**
     * 后台查询系统用户
     * @param request
     * @return
     *//*
    public CommonResult getAdminInfo(GetAdminInfoReqVo request) {
        long startTime = System.currentTimeMillis();
        CommonResult<LoginRespVo> result = new  CommonResult();
        LoginRespVo response = new LoginRespVo();
        Set<Integer> rootMenuIds = new HashSet<>();
        try {
            CsSecurityUserExample csSecurityUserExample = new CsSecurityUserExample();
            CsSecurityUserExample.Criteria criteria = csSecurityUserExample.or();
            criteria.andIdEqualTo(request.getId());
            List<CsSecurityUser> ulist = csSecurityUserService.selectByLoginNameAndPassword(csSecurityUserExample);
            if(CollectionUtil.notEmptyCollection(ulist)){
                response.setCsSecurityUser(ulist.get(0));
                //获取用户角色
                List<CsSecurityRole> roleList = csSecurityRoleService.selectByUserId(ulist.get(0).getId());
                if(CollectionUtil.notEmptyCollection(roleList)){
                    roleList.forEach(r->{
                        response.addRoleList(r);
                    });
                }
                //获取权限菜单
                //找到每个对象中的rootIds，转化成int。添加到set集合中。
                List<CsSecurityRoleMenu> roleMenuList = csSecurityRoleMenuService.findRoleMenuByUserId(ulist.get(0).getId());
                roleMenuList.forEach(m->{
                    if(!StringUtils.isBlank(m.getRootids())){
                        List<Integer> rootIds = StringUtils.splitToIntegerList(m.getRootids().trim(),CdfConsts.COMMA);
                        rootMenuIds.addAll(rootIds);
                    }
                });
                response.setMenuList(rootMenuIds);
                result.setData(response);
                result.setCode(ResultCode.SUCCESS.getCode());
                result.setMessage("获取成功。");
            }else{
                result.setCode(ResultCode.SUCCESS.getCode());
                result.setMessage("查无此人!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.FAILED.getCode());
            result.setMessage("查询异常。");
        }
        long endTime = System.currentTimeMillis();
        logger.info("调用adminIfo接口总用时：{}",endTime - startTime);
        return result;
    }*/


    /**
     * rolestr转换
     * @param rolestr
     * @return
     */
    public List<Integer> convertRole(String rolestr){
        List<Integer> rolelist = new ArrayList<>();
        try {
            String[] roleid = rolestr.split(",");
            for (int i = 0; i < roleid.length; i++) {
                rolelist.add(Integer.parseInt(roleid[i].trim()));
            }
        } catch (NumberFormatException e) {
            logger.info("角色转换集合异常，请求的角色字符串为：{}",rolestr);
            e.printStackTrace();
        }
        return rolelist;
    }

    /**
     * description: 更新最后登录时间
     * @Param:
     * @Return:
     * @Author: hanyaguang
     * @Date: 2021/11/17 17:31
     */
   /* public void updateLastLoginTime(String id){
       if(!StringUtils.isBlank(id)){
           try {
               Integer memberId = Integer.parseInt(id);
               if(!ObjectUtils.isEmpty(memberId)){
                   CsSecurityUserExample csSecurityUserExample = new CsSecurityUserExample();
                   CsSecurityUserExample.Criteria criteria = csSecurityUserExample.or();
                   criteria.andIdEqualTo(memberId);
                   List<CsSecurityUser>  csSecurityUserList = csSecurityUserService.selectByExample(csSecurityUserExample);
                   if(!ObjectUtils.isEmpty(csSecurityUserList)){
                       CsSecurityUser csSecurityUser = csSecurityUserList.get(0);
                       csSecurityUser.setLastlogintime(new Date());
                       csSecurityUserService.updateByExampleSelective(csSecurityUser,csSecurityUserExample);
                   }
               }
           } catch (NumberFormatException e) {
               logger.info("更新后台用户最后登录时间异常，异常信息：{}",e.toString());
               e.printStackTrace();
           }
       }
    }*/
}
