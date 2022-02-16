package com.cdf.mall.mapper.second;

import com.cdf.mall.model.second.CdfUser;
import com.cdf.mall.model.second.CdfUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface CdfUserMapper {
    long countByExample(CdfUserExample example);

    int deleteByExample(CdfUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CdfUser record);

    int insertSelective(CdfUser record);

    List<CdfUser> selectByExample(CdfUserExample example);

    CdfUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CdfUser record, @Param("example") CdfUserExample example);

    int updateByExample(@Param("record") CdfUser record, @Param("example") CdfUserExample example);

    int updateByPrimaryKeySelective(CdfUser record);

    int updateByPrimaryKey(CdfUser record);
}