package com.silentao.paractical.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @Description GuavaCache结合Redis的使用
 * @Author chentao10
 * @Date 2022/6/4 15:17
 **/
public class GuavaCacheWithRedis {

    private static final Jedis JEDIS;
    private static final LoadingCache<String, String> CACHE;

    static {
        // 连接本地的Redis
        JEDIS = new Jedis("localhost");
        CACHE = CacheBuilder
                .newBuilder()
                .maximumSize(100)   // 最大容量是100
                .expireAfterAccess(60, TimeUnit.SECONDS)    // 被访问60秒后会被淘汰
                .refreshAfterWrite(30, TimeUnit.SECONDS)    // 30秒之后会被更新
                .removalListener(notify ->
                    System.out.println(notify.getCause())           // 被淘汰的原因
                )
                .build(createCacheLoader());
    }

    public static void main(String[] args) throws Exception {
        int i = 0;
        while (true) {
            System.out.println(i * 5 + ":" + CACHE.get("msg"));

            Thread.sleep(5000);
            i++;
        }
    }

    /**
     * 创建重新加载缓存的CacheLoader
     * @return
     */
    public static CacheLoader<String, String> createCacheLoader() {
        return new CacheLoader<String, String>() {

            @Override
            public String load(String key) {
                try {
                    System.out.println("get from redis key:" + key);

                    return JEDIS.get(key);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        };
    }
}
