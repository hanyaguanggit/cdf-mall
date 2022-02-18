package com.cdf.mall.service;

import com.cdf.mall.model.second.CdfUser;
import com.cdf.mall.model.second.CdfUserExample;

import java.util.List;

public interface UserService {

    /**
     * 主键查询用户信息
     * @param id
     * @return
     */
    CdfUser selectByPrimaryKey(Integer id);

    /**
     * 条件查询
     * @param example
     * @return
     */
    List<CdfUser> selectByExample(CdfUserExample example);
}
