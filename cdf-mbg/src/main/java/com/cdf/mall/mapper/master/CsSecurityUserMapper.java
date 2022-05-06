package com.cdf.mall.mapper.master;

import com.cdf.mall.model.master.CsSecurityUser;
import com.cdf.mall.model.master.CsSecurityUserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CsSecurityUserMapper {
    long countByExample(CsSecurityUserExample example);

    int deleteByExample(CsSecurityUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CsSecurityUser record);

    int insertSelective(CsSecurityUser record);

    List<CsSecurityUser> selectByExample(CsSecurityUserExample example);

    CsSecurityUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CsSecurityUser record, @Param("example") CsSecurityUserExample example);

    int updateByExample(@Param("record") CsSecurityUser record, @Param("example") CsSecurityUserExample example);

    int updateByPrimaryKeySelective(CsSecurityUser record);

    int updateByPrimaryKey(CsSecurityUser record);

    //查询包含角色信息的用户列表
   // List<CsSecurityUserExtension> selectUserListByRoleNameOrUserId(@Param("shopId") Integer shopId, @Param("roleName") String roleName, @Param("userId") Integer userId);
}