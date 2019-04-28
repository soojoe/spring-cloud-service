package com.soojoe.scaffold.service.helper;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 本地缓存对象
 *
 * <p>针对数据不经常调整，且数据量不大的数据可保存在本地缓存</p>
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/15 15:41
 */
public final class LocalCacheHelper {

  private static CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().maximumSize(2000).initialCapacity(10);
  private static Cache<String, Object> cache;
  static{
    cache =cacheBuilder.build();
  }

  /**
   * 添加缓存
   * @param key
   * @param value
   */
  public static void put(String key, Object value){
    cache.put(key, value);
  }


  /**
   * 删除缓存
   * @param key
   */
  public static void del(String key){
    cache.invalidate(key);
  }

  /**
   * 根据key取得缓存对象
   * @param key
   * @return
   */
  public static Object get(String key){
    return cache.getIfPresent(key);
  }

}
