package com.cdf.mall.dto.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description 保存文章请求体
 * @Author hanyaguang
 * @Date 2022/2/15 9:32
 * @Version 1.0
 */
public class SaveArticleReqVo {

    @NotBlank(message = "文章标题不能为空")
    private String title;

    //文章内容
    private String content;

    //文章作者
    private Integer userId;

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


}
