package com.cdf.mall.dto.resp;

/**
 * @Description 文章响应体
 * @Author hanyaguang
 * @Date 2022/2/17 15:59
 * @Version 1.0
 */
public class ArticleRespVo {

    private Integer id ;

    private String title;

    private String content;

    private Integer userId;

    private String userName;//作者

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
