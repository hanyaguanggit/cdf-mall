package com.cdf.mall.cache;

import com.cdf.mall.bean.Metrics;
import com.cdf.mall.bean.Statistics;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @Description 本地缓存抽象类
 * @Author hanyaguang
 * @Date 2022/5/20 14:28
 * @Version 1.0
 */
public abstract class AbstractLocalCache<T> {
    private static final Logger log = LoggerFactory.getLogger(AbstractLocalCache.class);
    private static final long DEFAULT_DURATION = 600L;
    private static final long MAXIMUM_SIZE = 10000L;
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private RetryTemplate retryTemplate;

    protected LoadingCache<String, T> loadingCache;

    public AbstractLocalCache() {
    }

    protected abstract String getKey(T value);

    protected abstract T load(String key);

    @PostConstruct
    private void init() {
        this.build();
    }

    public String cacheName() {
        return this.getClass().getName();
    }

    protected long duration() {
        return 600L;
    }

    protected long maximumSize() {
        return 10000L;
    }

    protected boolean async() {
        return false;
    }

    protected boolean nullable() {
        return false;
    }

    protected Consumer<T> updater() {
        return (value) -> {
            String key = this.getKey(value);
            this.put(key, value);
        };
    }

    protected void update(String args, Consumer<T> updater) {
    }

    protected void warm(String args, Consumer<T> updater) {
    }

    @EventListener({ApplicationReadyEvent.class})
    public void onApplicationReadyEvent() {
        log.info("local cache warm up after application ready, cacheName={}", this.cacheName());
        RetryCallback<Boolean, RuntimeException> retryCallback = (context) -> {
            try {
                Consumer<T> updater = this.updater();
                this.warm((String)null, updater);
                log.info("warm up success, CacheName={}", this.cacheName());
            } catch (Exception var3) {
                log.error("local cache retry invoke error", var3);
                throw new RuntimeException(var3);
            }

            return true;
        };
        RecoveryCallback<Boolean> recoveryCallback = (context) -> {
            this.alarm("应用启动预热缓存失败");
            return false;
        };
        if (this.async()) {
            this.taskExecutor.execute(() -> {
                this.retryTemplate.execute(retryCallback, recoveryCallback);
            });
        } else {
            this.retryTemplate.execute(retryCallback, recoveryCallback);
        }

    }

    private void build() {
        long duration = this.duration();
        this.loadingCache = Caffeine.newBuilder().recordStats().maximumSize(this.maximumSize()).refreshAfterWrite(duration + 0L, TimeUnit.SECONDS).build(new CacheLoader<String, T>() {
            public T load(String key) {
                AbstractLocalCache.log.debug("local cache load, cacheName={}, key={}", AbstractLocalCache.this.cacheName(), key);

                try {
                    T value = AbstractLocalCache.this.load(key);
                    if (value == null && !AbstractLocalCache.this.nullable()) {
                        AbstractLocalCache.log.debug("local cache load return null, cacheName={}, key={}", AbstractLocalCache.this.cacheName(), key);
                    }

                    return value;
                } catch (Exception var3) {
                    AbstractLocalCache.log.error("local cache load error, cacheName={}, key={}", new Object[]{AbstractLocalCache.this.cacheName(), key, var3});
                    return null;
                }
            }
        });
        log.info("[Local Cache Build] " + this.cacheName());
    }

    public T get(String key) {
        try {
            return this.loadingCache.get(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            log.error("get data from local cache error, cacheName={}, key={}", new Object[]{this.cacheName(), key, var3});
            return null;
        }
    }

    public T get() {
        try {
            return this.loadingCache.get("1");
        } catch (Exception var2) {
            var2.printStackTrace();
            log.error("get data from local cache error, cacheName={}, key={}", new Object[]{this.cacheName(), "1", var2});
            return null;
        }
    }

    public T getIfPresent(String key) {
        try {
            return this.loadingCache.getIfPresent(key);
        } catch (Exception var3) {
            log.error("getIfPresent data from local cache error", var3);
            return null;
        }
    }

    public boolean contains(String key) {
        T value = this.loadingCache.getIfPresent(key);
        return value != null;
    }

    public void put(String key, T value) {
        this.loadingCache.put(key, value);
    }

    public void putAll(Map<? extends String, T> map) {
        this.loadingCache.putAll(map);
    }

    public void putAll(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach((item) -> {
                if (item != null) {
                    String key = this.getKey(item);
                    this.loadingCache.put(key, item);
                }

            });
        }
    }

    public void delete(String key) {
        this.loadingCache.invalidate(key);
    }

    public void delete(List<String> keys) {
        this.loadingCache.invalidateAll(keys);
    }

    public void deleteAll() {
        this.loadingCache.invalidateAll();
    }

    public Statistics statistics() {
        Statistics statistics = new Statistics();
        CacheStats stats = this.loadingCache.stats();
        statistics.setRequestCount(stats.requestCount());
        statistics.setHitCount(stats.hitCount());
        statistics.setHitRate(stats.hitRate());
        statistics.setMissCount(stats.missCount());
        statistics.setMissRate(stats.missRate());
        statistics.setLoadCount(stats.loadCount());
        statistics.setLoadSuccessCount(stats.loadSuccessCount());
        statistics.setTotalLoadTime(stats.totalLoadTime());
        statistics.setAverageLoadPenalty(stats.averageLoadPenalty());
        statistics.setEvictionCount(stats.evictionCount());
        ConcurrentMap<String, T> map = this.loadingCache.asMap();
        return statistics;
    }

    public long count() {
        long count = 0L;
        Collection<T> values = this.loadingCache.asMap().values();
        if (CollectionUtils.isEmpty(values)) {
            return count;
        } else {
            Iterator var4 = values.iterator();

            while(var4.hasNext()) {
                T t = (T) var4.next();
                if (t != null) {
                    ++count;
                }
            }

            return count;
        }
    }

    protected void alarm(String message) {
        log.info("[本地缓存告警] CacheName={}, Message={}", this.getClass().getName(), message);
    }

    @Scheduled(
            fixedRate = 2000L
    )
    public void metrics() {
        try {
            Metrics.stat(this, this.statistics());
        } catch (Exception var2) {
            log.error("metrics error", var2);
        }

    }
}
