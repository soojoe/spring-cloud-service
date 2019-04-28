package com.soojoe.scaffold.service.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cglib.beans.BeanCopier;

/**
 * Bean拷贝工具
 *
 * <p>主要用于类之间的类型转换</p>
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/15 14:15
 */
@Slf4j
public final class BeanCopyHelper {

  //缓存beanCopiers
  private static final Map<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<>();

  private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
    return srcClazz.getName() + destClazz.getName();
  }

  private static <FROM, TO> BeanCopier getBeanCopier(Class<FROM> fromClass, Class<TO> toClass) {
    String key = genKey(fromClass, toClass);
    BeanCopier copier = null;
    if (!BEAN_COPIERS.containsKey(key)) {
      copier = BeanCopier.create(fromClass, toClass, false);
      BEAN_COPIERS.put(key, copier);
    } else {
      copier = BEAN_COPIERS.get(key);
    }
    return copier;
  }

  /**
   * 对象拷贝,浅拷贝
   *
   * @param srcObj 源对象
   * @param toClass 目标对象类,要求有默认构造函数
   */
  public static <TO> TO copy(Object srcObj, Class<TO> toClass) {
    if (srcObj == null) {
      //safe
      return null;
    }

    final Class<?> fromClass = srcObj.getClass();
    BeanCopier copier = getBeanCopier(fromClass, toClass);
    try {
      final TO toObject = toClass.newInstance();
      copier.copy(srcObj, toObject, null);
      return toObject;
    } catch (Exception e) {
      log.warn("copy object error", e);
    }

    return null;
  }

  /**
   * 复制列表,浅拷贝
   *
   * @param srcObjs 源对象s
   * @param toClass 目标对象类,要求有默认构造函数
   */
  public static <TO> List<TO> copyList(List srcObjs, Class<TO> toClass) {
    if (CollectionUtils.isEmpty(srcObjs)) {
      return new ArrayList<>(0);
    }
    List<TO> resultList = new ArrayList<>(srcObjs.size());
    for (Object srcObj : srcObjs) {
      final TO destObject = BeanCopyHelper.copy(srcObj, toClass);
      if (destObject != null) {
        resultList.add(destObject);
      }
    }

    return resultList;
  }
}
