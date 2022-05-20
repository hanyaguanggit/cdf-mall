package com.cdf.mall.bean;

/**
 * @Description 统计数据实体类
 * @Author hanyaguang
 * @Date 2022/5/20 14:35
 * @Version 1.0
 */
public class Statistics {
    private long requestCount;
    private long hitCount;
    private double hitRate;
    private long missCount;
    private double missRate;
    private long loadCount;
    private long loadSuccessCount;
    private long totalLoadTime;
    private double averageLoadPenalty;
    private long evictionCount;
    private long totalSize;

    public Statistics() {
    }

    public long getRequestCount() {
        return this.requestCount;
    }

    public long getHitCount() {
        return this.hitCount;
    }

    public double getHitRate() {
        return this.hitRate;
    }

    public long getMissCount() {
        return this.missCount;
    }

    public double getMissRate() {
        return this.missRate;
    }

    public long getLoadCount() {
        return this.loadCount;
    }

    public long getLoadSuccessCount() {
        return this.loadSuccessCount;
    }

    public long getTotalLoadTime() {
        return this.totalLoadTime;
    }

    public double getAverageLoadPenalty() {
        return this.averageLoadPenalty;
    }

    public long getEvictionCount() {
        return this.evictionCount;
    }

    public long getTotalSize() {
        return this.totalSize;
    }

    public void setRequestCount(final long requestCount) {
        this.requestCount = requestCount;
    }

    public void setHitCount(final long hitCount) {
        this.hitCount = hitCount;
    }

    public void setHitRate(final double hitRate) {
        this.hitRate = hitRate;
    }

    public void setMissCount(final long missCount) {
        this.missCount = missCount;
    }

    public void setMissRate(final double missRate) {
        this.missRate = missRate;
    }

    public void setLoadCount(final long loadCount) {
        this.loadCount = loadCount;
    }

    public void setLoadSuccessCount(final long loadSuccessCount) {
        this.loadSuccessCount = loadSuccessCount;
    }

    public void setTotalLoadTime(final long totalLoadTime) {
        this.totalLoadTime = totalLoadTime;
    }

    public void setAverageLoadPenalty(final double averageLoadPenalty) {
        this.averageLoadPenalty = averageLoadPenalty;
    }

    public void setEvictionCount(final long evictionCount) {
        this.evictionCount = evictionCount;
    }

    public void setTotalSize(final long totalSize) {
        this.totalSize = totalSize;
    }
}
