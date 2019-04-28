package com.soojoe.scaffold.service.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 * Json工具类
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/17 17:44
 */
public final class JsonHelper {

    private static final ObjectMapper deserializationMapper=new ObjectMapper().
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).registerModule(new AfterburnerModule());

    private static final ObjectMapper serializationMapper=new ObjectMapper().registerModule(new AfterburnerModule());


  /**
   * 对象序列化为json字符串
   * @param obj
   * @return
   */
  public static String toJson(Object obj) {
    try {
      return serializationMapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException("Object转JSON出错", e);
    }
  }

  /**
   * 从Json反序列化为对象
   * @param json
   * @param type
   * @param <T>
   * @return
   */
    public static  <T> T getObjectFromJson(String json , Class<T> type) {
      try {
        if (StringUtils.isBlank(json)) {
          return null;
        }
        return deserializationMapper.readValue(json,type);
      } catch (Exception e) {
        throw new RuntimeException("JSON转Object出错", e);
      }
    }

  /**
   * 从Json反序列化为对象
   * @param json
   * @param type
   * @param <T>
   * @return
   */
    public static  <T> T getObjectFromJson(String json , ParameterizedType type) {
      try {
        if (StringUtils.isBlank(json)) {
          return null;
        }

        return deserializationMapper.readValue(json, deserializationMapper.getTypeFactory().constructType(type));
      } catch (Exception e) {
        throw new RuntimeException("JSON转Object出错", e);
      }
    }

  /**
   * 从Json byte数组反序列化为对象
   * @param json
   * @param type
   * @param <T>
   * @return
   */
    public static  <T> T getObjectFromJson(byte[] json , ParameterizedType type) {
      try {
        if (json == null || json.length == 0) {
          return null;
        }

        return deserializationMapper.readValue(json, deserializationMapper.getTypeFactory().constructType(type));
      } catch (Exception e) {
        throw new RuntimeException("JSON转Object出错", e);
      }
    }

  /**
   * 从Json反序列化为对象
   * @param json
   * @param typeReference
   * @return
   */
    public static <T> T getObjectFromJson(String json, TypeReference<T> typeReference){
      if (StringUtils.isBlank(json)) {
        return null;
      }
      try {
        return deserializationMapper.readValue(json, typeReference);
      } catch (Exception e){
        throw new RuntimeException("JSON转Object出错", e);
      }
    }

  /**
   * 从Json反序列化为json节点
   * @param json
   * @return
   */
    public static JsonNode getJsonNode(String json) {
      try {
        if (StringUtils.isBlank(json)) {
          return null;
        }
        return deserializationMapper.readTree(json);
      } catch (Exception e) {
        throw new RuntimeException("JSON转JsonNode出错,json:" + json, e);
      }
    }

  /**
   * 从Json反序列化为List对象
   * @param json
   * @return
   */
    public static <T> List<T> getListFromJson(String json , Class<T> itemType) {
      try {
        if (StringUtils.isBlank(json)) {
          return null;
        }
        JavaType listType = deserializationMapper.getTypeFactory().constructCollectionType(ArrayList.class, itemType);
        return deserializationMapper.readValue(json, listType);
      } catch (Exception e) {
        throw new RuntimeException("JSON转Object出错", e);
      }

    }



  /**
   * 对象序列化为json byte数组
   * @param object
   * @return
   */
    public static byte[] writeObjectAsBytes(Object object) {
      try {
        return serializationMapper.writeValueAsBytes(object);
      } catch (Exception e) {
        throw new RuntimeException("Object转Byte出错", e);
      }
    }

  /**
   * 从二进制数组中序列化为对象
   * @param bytes
   * @param type
   * @param <T>
   * @return
   */
    public static <T> T getObjectFromBytes(byte[] bytes, Class<T> type){
      try {
        return deserializationMapper.readValue(bytes, type);
      } catch (Exception e) {
        throw new RuntimeException("Byte转Object出错", e);
      }
    }

  /**
   * 从二进制数组中序列化为对象
   * @param bytes
   * @param GenericsType
   * @param <T>
   * @return
   */
    public static <T> T getObjectFromBytesWithTypeConstruct(byte[] bytes, Class<T> GenericsType, Class... innerTypes){
      try {
        JavaType javaType = deserializationMapper.getTypeFactory().constructType(
            ParameterizedTypeImpl.make(GenericsType, innerTypes, null));
        return deserializationMapper.readValue(bytes, javaType);
      } catch (Exception e) {
        throw new RuntimeException("Byte转Object出错", e);
      }
    }

  /**
   * 从二进制数组中序列化为对象
   * @param json
   * @param GenericsType
   * @param <T>
   * @return
   */
    public static <T> T getObjectFromJsonWithTypeConstruct(String json, Class<T> GenericsType, Class... innerTypes){
      try {
        JavaType javaType = constructType(GenericsType, innerTypes);
        return deserializationMapper.readValue(json, javaType);
      } catch (Exception e) {
        throw new RuntimeException("JSON转Object出错", e);
      }
    }


    public static JavaType constructType(Class GenericsType, Class... innerTypes){
      return deserializationMapper.getTypeFactory().constructType(ParameterizedTypeImpl.make(GenericsType, innerTypes, null));
    }

}
