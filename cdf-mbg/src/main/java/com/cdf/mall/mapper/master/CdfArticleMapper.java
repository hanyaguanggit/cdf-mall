package com.cdf.mall.mapper.master;

import com.cdf.mall.model.master.CdfArticle;
import com.cdf.mall.model.master.CdfArticleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CdfArticleMapper {
    long countByExample(CdfArticleExample example);

    int deleteByExample(CdfArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CdfArticle record);

    int insertSelective(CdfArticle record);

    List<CdfArticle> selectByExample(CdfArticleExample example);

    CdfArticle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CdfArticle record, @Param("example") CdfArticleExample example);

    int updateByExample(@Param("record") CdfArticle record, @Param("example") CdfArticleExample example);

    int updateByPrimaryKeySelective(CdfArticle record);

    int updateByPrimaryKey(CdfArticle record);
}