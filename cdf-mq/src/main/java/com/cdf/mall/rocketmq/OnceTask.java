package com.cdf.mall.rocketmq;


import java.util.Date;

/**
 * @Description 单次任务
 * @Author hanyaguang
 * @Date 2022/5/24 15:54
 * @Version 1.0
 */
public class OnceTask extends Task {
    private String status;
    private Integer round;
    private Integer interval;
    private Integer retries;
    private Integer type;
    private String index1;
    private String index2;
    private String index3;
    private String index4;
    private String index5;
    private String index6;
    private String index7;
    private String index8;
    private String index9;
    private String index10;
    private Date deleteTime;

    public OnceTask() {
    }

    public OnceTask(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public Integer getRound() {
        return this.round;
    }

    public Integer getInterval() {
        return this.interval;
    }

    public Integer getRetries() {
        return this.retries;
    }

    public Integer getType() {
        return this.type;
    }

    public String getIndex1() {
        return this.index1;
    }

    public String getIndex2() {
        return this.index2;
    }

    public String getIndex3() {
        return this.index3;
    }

    public String getIndex4() {
        return this.index4;
    }

    public String getIndex5() {
        return this.index5;
    }

    public String getIndex6() {
        return this.index6;
    }

    public String getIndex7() {
        return this.index7;
    }

    public String getIndex8() {
        return this.index8;
    }

    public String getIndex9() {
        return this.index9;
    }

    public String getIndex10() {
        return this.index10;
    }

    public Date getDeleteTime() {
        return this.deleteTime;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setRound(final Integer round) {
        this.round = round;
    }

    public void setInterval(final Integer interval) {
        this.interval = interval;
    }

    public void setRetries(final Integer retries) {
        this.retries = retries;
    }

    public void setType(final Integer type) {
        this.type = type;
    }

    public void setIndex1(final String index1) {
        this.index1 = index1;
    }

    public void setIndex2(final String index2) {
        this.index2 = index2;
    }

    public void setIndex3(final String index3) {
        this.index3 = index3;
    }

    public void setIndex4(final String index4) {
        this.index4 = index4;
    }

    public void setIndex5(final String index5) {
        this.index5 = index5;
    }

    public void setIndex6(final String index6) {
        this.index6 = index6;
    }

    public void setIndex7(final String index7) {
        this.index7 = index7;
    }

    public void setIndex8(final String index8) {
        this.index8 = index8;
    }

    public void setIndex9(final String index9) {
        this.index9 = index9;
    }

    public void setIndex10(final String index10) {
        this.index10 = index10;
    }

    public void setDeleteTime(final Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
