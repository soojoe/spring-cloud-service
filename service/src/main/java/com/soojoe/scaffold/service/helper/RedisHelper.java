package com.soojoe.scaffold.service.helper;


import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * Redis util类
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/28 7:31
 */
@Slf4j
@Component
public class RedisHelper {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  /**
   * 从redis获取对象
   *
   * @param key 存储对象的键
   * @param clazz 对象类型对应的class
   * @return 获取到的对象
   */
  public <T> T getObject(String key, Class<T> clazz) {
    if (StringUtils.isBlank(key) || Objects.isNull(clazz)) {
      return null;
    }
    try {
      String value = stringRedisTemplate.opsForValue().get(key);
      return JsonHelper.getObjectFromJson(value,clazz);
    } catch (Exception e) {
      log.error("read string error, key={}", key, e);
      return null;
    }
  }


  /**
   * 向redis存储对象
   *
   * @param key 存储对象对应的键
   * @param t 要存储的对象
   * @param ttl 该键过期时间
   */
  public <T> void putObject(String key, T t, int ttl) {
    if (StringUtils.isBlank(key) || Objects.isNull(t)) {
      return;
    }
    try {
      stringRedisTemplate.opsForValue().set(key, JsonHelper.toJson(t), ttl, TimeUnit.SECONDS);
    } catch (Exception e) {
      log.error("write string error, key={}, ttl={}", key, ttl, e);
    }
  }


  /**
   * 获取string
   *
   * @param key 存储string的键
   * @return 获取的string
   */
  public String get(String key) {
    if (StringUtils.isBlank(key)) {
      return null;
    }
    try {
      return stringRedisTemplate.opsForValue().get(key);
    } catch (Exception e) {
      log.error("read string error, key={}", key, e);
      return null;
    }
  }


  /**
   * 向redis存储string
   *
   * @param key 存储对象对应的键
   * @param value 要存储键的值
   */
  public void set(String key, String value) {
    if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
      return;
    }
    try {
      stringRedisTemplate.opsForValue().set(key, value);
    } catch (Exception e) {
      log.error("write string error, key={}", key, e);
    }
  }


  /**
   * 向redis存储string
   *
   * @param key 存储对象对应的键
   * @param value 要存储键的值
   * @param ttl 该键过期时间
   */
  public void setex(String key, String value, int ttl) {
    if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
      return;
    }
    try {
      stringRedisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
    } catch (Exception e) {
      log.error("write string error, key={}, ttl={}", key, ttl, e);
    }
  }


  /**
   * 对象不存在时向redis存储string
   *
   * @param key 存储对象对应的键
   * @param value 要存储键的值
   * @return 之前不存在时返回true，存在时返回false
   */
  public Boolean setnx(String key, String value) {
    if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
      return null;
    }
    try {
      return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    } catch (Exception e) {
      log.error("write string error, key={}", key, e);
      return null;
    }
  }


  /**
   * 将对应的键设置为新值，并返回旧值
   *
   * @param key 存储对象的键
   * @param value 要设置的新值
   * @return 返回的旧值
   */
  public String getSet(String key, String value) {
    if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
      return null;
    }
    try {
      return stringRedisTemplate.opsForValue().getAndSet(key, value);
    } catch (Exception e) {
      log.error("write string error, key={}", key, e);
      return null;
    }
  }


  /**
   * 删除指定的键
   *
   * @param key 要删除的键
   * @return 是否删除成功
   */
  public Boolean del(String key) {
    if (StringUtils.isBlank(key)) {
      return false;
    }
    try {
      return stringRedisTemplate.delete(key);
    } catch (Exception e) {
      log.error("del key error, key={}", key, e);
      return false;
    }
  }


  /**
   * 设置键的过期时间
   *
   * @param key 要设置的键
   * @param ttl 过期时间，单位：秒
   * @return 设置结果
   */
  public Boolean expire(String key, int ttl) {
    if (StringUtils.isBlank(key)) {
      return false;
    }
    try {
      return stringRedisTemplate.expire(key, ttl, TimeUnit.SECONDS);
    } catch (Exception e) {
      log.error("expire key error, key={}, ttl={}", key, ttl, e);
      return false;
    }
  }


  /**
   * 增加一个键的值
   *
   * @param key 操作的键
   * @param delta 要增加的量
   */
  public Long incr(String key, long delta) {
    if (StringUtils.isBlank(key)) {
      return 0L;
    }
    try {
      return stringRedisTemplate.opsForValue().increment(key, delta);
    } catch (Exception e) {
      log.error("incr key error, key={}, delta={}", key, delta, e);
      return 0L;
    }
  }

  /**
   * 增加一个键的值
   *
   * @param key 操作的键
   * @param delta 要增加的量
   * @param ttl 设置的过期时间
   */
  public Long incr(String key, long delta, int ttl) {
    if (StringUtils.isBlank(key)) {
      return 0L;
    }
    try {
      return stringRedisTemplate.opsForValue().increment(key, delta);
    } catch (Exception e) {
      log.error("incr key error, key={}, delta={}", key, delta, e);
      return 0L;
    }
  }

  /**
   * 向有序集合添加成员
   *
   * @param key 有序集合的键
   * @param members 要添加的成员
   * @param ttl 键的过期时间
   */
  public Long zadd(String key, Map<String, Double> members, Integer ttl) {
    if (StringUtils.isBlank(key) || CollectionUtils.isEmpty(members)) {
      return 0L;
    }
    try {
      Set<ZSetOperations.TypedTuple<String>> collect = members.entrySet().parallelStream()
          .map(entry -> new
              DefaultTypedTuple<>(entry.getKey(), entry.getValue())).collect(Collectors.toSet());
      Long add = stringRedisTemplate.opsForZSet().add(key, collect);
      if (Objects.nonNull(ttl)) {
        stringRedisTemplate.expire(key, ttl, TimeUnit.SECONDS);
      }
      return add;
    } catch (Exception e) {
      log.error("write zset error, key={}, members={}, ttl={}", key, JsonHelper.toJson(members),
          ttl, e);
      return 0L;
    }
  }


  /**
   * 从有序集合中删除成员
   *
   * @param key 有序集合的键
   * @param members 要删除的成员
   * @return 删除的个数
   */
  public Long zrem(String key, Object... members) {
    if (StringUtils.isBlank(key) || ArrayUtils.isEmpty(members)) {
      return 0L;
    }
    try {
      return stringRedisTemplate.opsForZSet().remove(key, members);
    } catch (Exception e) {
      log.error("del zset error, key={}, members={}", key, JsonHelper.toJson(members), e);
      return 0L;
    }
  }


  /**
   * 查询一个key是否存在
   */
  public Boolean hasKey(String key) {
    try {
      return stringRedisTemplate.hasKey(key);
    } catch (Exception e) {
      log.error("query HasKey error, key={}", key, e);
      return false;
    }
  }

  /**
   * 获取有序集合中的成员
   *
   * @param key 有序集合键
   * @param start 开始位置
   * @param end 结束位置
   * @return 有序集合数据
   */
  public Set<String> zrevrange(String key, int start, int end) {
    try {
      return stringRedisTemplate.opsForZSet().reverseRange(key, start, end);
    } catch (Exception e) {
      log.error("zrevrange key error, key={}, start={}, end={}", key, start, end, e);
      return Collections.emptySet();
    }
  }


  /**
   * 向集合中增加成员
   *
   * @param key 集合键
   * @param ttl 过期时间
   * @param members 要添加的成员
   * @return 添加的数量
   */
  public long sadd(String key, Integer ttl, String... members) {
    if (StringUtils.isBlank(key) || members.length == 0) {
      return 0;
    }
    try {
      Long add = stringRedisTemplate.opsForSet().add(key, members);
      if (Objects.nonNull(ttl)) {
        stringRedisTemplate.expire(key, ttl, TimeUnit.SECONDS);
      }
      return add;
    } catch (Exception e) {
      log.error("write set error, key={}, members={}", key, JsonHelper.toJson(members), e);
      return 0;
    }
  }


  /**
   * 从集合中随机获取一个元素
   *
   * @param key 集合键
   * @return 随机获取到的成员
   */
  public String srandmember(String key) {
    if (StringUtils.isBlank(key)) {
      return null;
    }
    try {
      return stringRedisTemplate.opsForSet().randomMember(key);
    } catch (Exception e) {
      log.error("read set error, key={}", key, e);
      return null;
    }
  }


  /**
   * 从集合中随机获取并移除一个成员
   *
   * @param key 集合键
   * @return 随机弹出的一个成员
   */
  public String spop(String key) {
    if (StringUtils.isBlank(key)) {
      return null;
    }
    try {
      return stringRedisTemplate.opsForSet().pop(key);
    } catch (Exception e) {
      log.error("read set error, key={}", key, e);
      return null;
    }
  }

  /**
   * 向链表右侧加入成员
   *
   * @param key 要操作的键
   * @param value 要加入的值
   * @param ttl 过期时间
   * @return 操作结果
   */
  public Long rpush(String key, String value, Integer ttl) {
    if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
      return 0L;
    }
    try {
      Long ret = stringRedisTemplate.opsForList().rightPush(key, value);
      if (Objects.nonNull(ttl)) {
        stringRedisTemplate.expire(key, ttl, TimeUnit.SECONDS);
      }
      return ret;
    } catch (Exception e) {
      log.error("write list error, key={}, value={}, ttl={}", key, value, ttl, e);
      return 0L;
    }
  }


  /**
   * 从链表左侧获取元素
   *
   * @param key 要操作的键
   * @return 获取的结果
   */
  public String lpop(String key) {
    if (StringUtils.isBlank(key)) {
      return null;
    }
    try {
      return stringRedisTemplate.opsForList().leftPop(key);
    } catch (Exception e) {
      log.error("read list error, key={}", key, e);
      return null;
    }
  }
}
