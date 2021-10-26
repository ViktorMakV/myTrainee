package com.restapp.configuration;

import com.model.caches.CacheInterface;
import com.model.caches.FirstLevelCacheImpl;
import com.model.caches.SecondLevelCache;
import com.restapp.model.Unit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Viktor Makarov
 */
@Configuration
@PropertySource("classpath:cache.properties")
public class CacheConfiguration {
    @Value("${cache.active}")
    private boolean isCacheActive;

    @Bean
    public CacheInterface<Unit> cacheInterface() {
        CacheInterface<Unit> cache = null;
        if (isCacheActive) {
            cache = new FirstLevelCacheImpl<>(new SecondLevelCache<>());
        }
        return cache;
    }
}
