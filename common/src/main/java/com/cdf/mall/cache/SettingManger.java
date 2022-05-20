package com.cdf.mall.cache;

import com.cdf.mall.bean.SystemSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * @Description 系统参数缓存管理器
 * @Author hanyaguang
 * @Date 2022/5/20 15:11
 * @Version 1.0
 */
public class SettingManger {

    @Autowired
    @Lazy
    private SystemSettingCache systemSettingCache;

    public SettingManger() {
    }

    public String get(String key) {
        SystemSetting systemSetting = (SystemSetting)this.systemSettingCache.get(key);
        return systemSetting != null ? systemSetting.getSettingValue() : null;
    }
}
