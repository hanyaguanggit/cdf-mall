package com.cdf.mall.service.master;

import com.cdf.mall.mapper.master.CdfArticleMapper;
import com.cdf.mall.model.master.CdfArticle;
import com.cdf.mall.model.master.CdfArticleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 文章实现类
 * @Author hanyaguang
 * @Date 2022/2/10 15:24
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private CdfArticleMapper articleMapper;

    @Override
    public int deleteByExample(CdfArticleExample example) {
        return articleMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CdfArticle record) {
        return articleMapper.insert(record);
    }

    @Override
    public int insertSelective(CdfArticle record) {
        return articleMapper.insertSelective(record);
    }

    @Override
    public List<CdfArticle> selectByExample(CdfArticleExample example) {
        return articleMapper.selectByExample(example);
    }

    @Override
    public CdfArticle selectByPrimaryKey(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(CdfArticle record, CdfArticleExample example) {
        return articleMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(CdfArticle record, CdfArticleExample example) {
        return articleMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(CdfArticle record) {
        return articleMapper.updateByPrimaryKeySelective(record);
    }

}
