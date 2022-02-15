package com.cdf.mall.service;

import com.cdf.mall.mapper.CdfUserMapper;
import com.cdf.mall.model.CdfUser;
import com.cdf.mall.model.CdfUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户有关
 * @Author hanyaguang
 * @Date 2022/2/15 21:25
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private CdfUserMapper cdfUserMapper;

    @Override
    public int insert(CdfUser record) {
        return cdfUserMapper.insert(record);
    }

    @Override
    public int insertSelective(CdfUser record) {
        return cdfUserMapper.insertSelective(record);
    }

    @Override
    public List<CdfUser> selectByExample(CdfUserExample example) {
        return cdfUserMapper.selectByExample(example);
    }

    @Override
    public CdfUser selectByPrimaryKey(Integer id) {
        return cdfUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(CdfUser record, CdfUserExample example) {
        return cdfUserMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(CdfUser record, CdfUserExample example) {
        return cdfUserMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(CdfUser record) {
        return cdfUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CdfUser record) {
        return cdfUserMapper.updateByPrimaryKey(record);
    }
}
