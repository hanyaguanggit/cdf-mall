package com.cdf.mall.service;

import com.cdf.test.db1.model.CdfArticle;
import com.cdf.test.db1.model.CdfArticleExample;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticelService {

    int deleteByExample(CdfArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CdfArticle record);

    int insertSelective(CdfArticle record);

    List<CdfArticle> selectByExample(CdfArticleExample example);

    CdfArticle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CdfArticle record, @Param("example") CdfArticleExample example);

    int updateByExample(@Param("record") CdfArticle record, @Param("example") CdfArticleExample example);

    int updateByPrimaryKeySelective(CdfArticle record);
}
