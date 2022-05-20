package com.cdf.mall.bean;

import com.cdf.mall.cache.AbstractLocalCache;
import com.cdf.mall.cache.AbstractLocalCacheRefresh;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.Gauge.Builder;
import io.prometheus.client.Gauge.Child;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * @Description 监控指标
 * @Author hanyaguang
 * @Date 2022/5/20 14:41
 * @Version 1.0
 */
public class Metrics {
    @Resource
    private PrometheusMeterRegistry prometheusMeterRegistry;
    private static final Gauge requestCount = ((Builder)((Builder)((Builder) Gauge.build().name("local_cache_request_count")).labelNames(new String[]{"cacheName"})).help("Help")).create();
    private static final Gauge hitCount = ((Builder)((Builder)((Builder)Gauge.build().name("local_cache_hit_count")).labelNames(new String[]{"cacheName"})).help("Help")).create();
    private static final Gauge hitRate = ((Builder)((Builder)((Builder)Gauge.build().name("local_cache_hit_rate")).labelNames(new String[]{"cacheName"})).help("Help")).create();
    private static final Gauge missCount = ((Builder)((Builder)((Builder)Gauge.build().name("local_cache_miss_count")).labelNames(new String[]{"cacheName"})).help("Help")).create();
    private static final Gauge missRate = ((Builder)((Builder)((Builder)Gauge.build().name("local_cache_miss_rate")).labelNames(new String[]{"cacheName"})).help("Help")).create();
    private static final Gauge loadCount = ((Builder)((Builder)((Builder)Gauge.build().name("local_cache_load_count")).labelNames(new String[]{"cacheName"})).help("Help")).create();
    private static final Gauge loadSuccessCount = ((Builder)((Builder)((Builder)Gauge.build().name("local_cache_load_success_count")).labelNames(new String[]{"cacheName"})).help("Help")).create();
    private static final Gauge loadExceptionCount = ((Builder)((Builder)((Builder)Gauge.build().name("local_cache_load_exception_count")).labelNames(new String[]{"cacheName"})).help("Help")).create();
    private static final Gauge loadExceptionRate = ((Builder)((Builder)((Builder)Gauge.build().name("local_cache_load_exception_rate")).labelNames(new String[]{"cacheName"})).help("Help")).create();
    private static final Gauge totalLoadTime = ((Builder)((Builder)((Builder)Gauge.build().name("local_cache_total_load_time")).labelNames(new String[]{"cacheName"})).help("Help")).create();
    private static final Gauge averageLoadPenalty = ((Builder)((Builder)((Builder)Gauge.build().name("local_cache_average_load_penalty")).labelNames(new String[]{"cacheName"})).help("Help")).create();
    private static final Gauge evictionCount = ((Builder)((Builder)((Builder)Gauge.build().name("local_cache_eviction_count")).labelNames(new String[]{"cacheName"})).help("Help")).create();
    private static final Gauge totalSize = ((Builder)((Builder)((Builder)Gauge.build().name("local_cache_total_size")).labelNames(new String[]{"cacheName"})).help("Help")).create();

    public Metrics() {
    }

    @PostConstruct
    private void init() {
        CollectorRegistry prometheusRegistry = this.prometheusMeterRegistry.getPrometheusRegistry();
        requestCount.register(prometheusRegistry);
        hitCount.register(prometheusRegistry);
        hitRate.register(prometheusRegistry);
        missCount.register(prometheusRegistry);
        missRate.register(prometheusRegistry);
        loadCount.register(prometheusRegistry);
        loadSuccessCount.register(prometheusRegistry);
        loadExceptionCount.register(prometheusRegistry);
        loadExceptionRate.register(prometheusRegistry);
        totalLoadTime.register(prometheusRegistry);
        averageLoadPenalty.register(prometheusRegistry);
        evictionCount.register(prometheusRegistry);
    }

    public static void stat(AbstractLocalCache<?> localCache, Statistics statistics) {
        ((Child)requestCount.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getRequestCount());
        ((Child)hitCount.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getHitCount());
        ((Child)hitRate.labels(new String[]{localCache.getClass().getName()})).set(statistics.getHitRate());
        ((Child)missCount.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getMissCount());
        ((Child)missRate.labels(new String[]{localCache.getClass().getName()})).set(statistics.getMissRate());
        ((Child)loadCount.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getLoadCount());
        ((Child)loadSuccessCount.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getLoadSuccessCount());
        ((Child)totalLoadTime.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getTotalLoadTime());
        ((Child)averageLoadPenalty.labels(new String[]{localCache.getClass().getName()})).set(statistics.getAverageLoadPenalty());
        ((Child)evictionCount.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getEvictionCount());
    }

    public static void stat(AbstractLocalCacheRefresh<?> localCache, Statistics statistics) {
        ((Child)requestCount.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getRequestCount());
        ((Child)hitCount.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getHitCount());
        ((Child)hitRate.labels(new String[]{localCache.getClass().getName()})).set(statistics.getHitRate());
        ((Child)missCount.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getMissCount());
        ((Child)missRate.labels(new String[]{localCache.getClass().getName()})).set(statistics.getMissRate());
        ((Child)loadCount.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getLoadCount());
        ((Child)loadSuccessCount.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getLoadSuccessCount());
        ((Child)totalLoadTime.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getTotalLoadTime());
        ((Child)averageLoadPenalty.labels(new String[]{localCache.getClass().getName()})).set(statistics.getAverageLoadPenalty());
        ((Child)evictionCount.labels(new String[]{localCache.getClass().getName()})).set((double)statistics.getEvictionCount());
    }
}
