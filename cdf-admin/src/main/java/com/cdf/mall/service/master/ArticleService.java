package com.cdf.mall.service.master;


import com.cdf.mall.model.master.CdfArticle;
import com.cdf.mall.model.master.CdfArticleExample;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleService {

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
