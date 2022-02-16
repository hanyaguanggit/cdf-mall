package com.cdf.mall.service.second;

import com.cdf.mall.model.second.CdfUser;
import com.cdf.mall.model.second.CdfUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    int insert(CdfUser record);

    int insertSelective(CdfUser record);

    List<CdfUser> selectByExample(CdfUserExample example);

    CdfUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CdfUser record, @Param("example") CdfUserExample example);

    int updateByExample(@Param("record") CdfUser record, @Param("example") CdfUserExample example);

    int updateByPrimaryKeySelective(CdfUser record);

    int updateByPrimaryKey(CdfUser record);
}
