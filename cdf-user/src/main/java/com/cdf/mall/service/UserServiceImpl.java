package com.cdf.mall.service;

import com.cdf.mall.mapper.second.CdfUserMapper;
import com.cdf.mall.model.second.CdfUser;
import com.cdf.mall.model.second.CdfUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户相关实现
 * @Author hanyaguang
 * @Date 2022/2/17 14:09
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private CdfUserMapper cdfUserMapper;

    @Override
    public CdfUser selectByPrimaryKey(Integer id) {
        return cdfUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CdfUser> selectByExample(CdfUserExample example) {
        return cdfUserMapper.selectByExample(example);
    }
}
