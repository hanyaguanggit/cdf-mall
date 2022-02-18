package com.cdf.mall.dto.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Description 文章列表查询入参
 * @Author hanyaguang
 * @Date 2022/2/17 15:09
 * @Version 1.0
 */
public class ArticleListReqVo {

    //文章标题
    private String title;
    //页码
    @Min(value = 1,message = "pageNum必须大于0")
    private Integer pageNum;

    //数量
    @Min(value = 1,message = "pageSize必须大于0")
    @Max(value = 100,message = "pageSize不能大于100")
    private Integer pageSize;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ArticleListReqVo(String title, @Min(value = 1, message = "pageNum必须大于0") Integer pageNum, @Min(value = 1, message = "pageSize必须大于0") @Max(value = 100, message = "pageSize不能大于100") Integer pageSize) {
        this.title = title;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public ArticleListReqVo() {
    }
}
