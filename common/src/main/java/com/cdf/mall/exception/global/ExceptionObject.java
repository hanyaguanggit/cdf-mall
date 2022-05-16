package com.cdf.mall.exception.global;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/5/16 15:17
 * @Version 1.0
 */
public class ExceptionObject {
    private Integer type;
    private String name;
    private String cla;
    private String content;

    public ExceptionObject() {
    }

    public Integer getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getCla() {
        return this.cla;
    }

    public String getContent() {
        return this.content;
    }

    public void setType(final Integer type) {
        this.type = type;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setCla(final String cla) {
        this.cla = cla;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}
