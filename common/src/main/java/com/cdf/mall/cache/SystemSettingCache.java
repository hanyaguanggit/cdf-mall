package com.cdf.mall.cache;

import com.cdf.mall.api.SettingFacade;
import com.cdf.mall.bean.SystemSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.function.Consumer;

/**
 * @Description 系统设置缓存类
 * @Author hanyaguang
 * @Date 2022/5/20 14:00
 * @Version 1.0
 */
public class SystemSettingCache extends AbstractLocalCache<SystemSetting> {
    private static final Logger log = LoggerFactory.getLogger(SystemSettingCache.class);
    @Autowired
    @Lazy
    private SettingFacade settingFacade;

    public SystemSettingCache() {
    }

    protected long duration() {
        return 6000L;
    }

    protected long maximumSize() {
        return 1000L;
    }

    protected boolean nullable() {
        return false;
    }

    protected void update(String args, Consumer<SystemSetting> updater) {
        log.info("BrandGoodsCountCache: update");
        if (args != null) {
            SystemSetting systemSetting = this.getValue(args);
            updater.accept(systemSetting);
        }

    }

    protected void warm(String args, Consumer<SystemSetting> updater) {
        log.info("BrandGoodsCountCache: warm");
        SystemSetting systemSetting1 = this.getValue("SYSTEM_H5_MINI_CLOSE");
        updater.accept(systemSetting1);
        SystemSetting systemSetting2 = this.getValue("SYSTEM_MINI_CLOSE");
        updater.accept(systemSetting2);
        SystemSetting systemSetting3 = this.getValue("SYSTEM_CLOSE_MESSAGE");
        updater.accept(systemSetting3);
        SystemSetting systemSetting4 = this.getValue("SHOP_LIVE_BUTTON");
        updater.accept(systemSetting4);
        SystemSetting systemSetting5 = this.getValue("SHOP_LIVE_ROOMID");
        updater.accept(systemSetting5);
    }

    protected String getKey(SystemSetting value) {
        return value.getSettingKey();
    }

    protected SystemSetting load(String key) {
        return this.getValue(key);
    }

    private SystemSetting getValue(String key) {
        SystemSetting systemSetting = this.settingFacade.findById(key);
        return systemSetting;
    }
}
