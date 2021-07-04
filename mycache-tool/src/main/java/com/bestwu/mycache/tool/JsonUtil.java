package com.bestwu.mycache.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/4 21:51 <br>
 */
public class JsonUtil {


    public static String toJsonString(Object object) {
        return toJSONString(object,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.DisableCircularReferenceDetect);
    }

    public static JSONObject parseJsonObject(String text) {
        return JSON.parseObject(text);
    }

    public static <T> T parseObject(String res, Type clazz) {
        return JSON.parseObject(res, clazz);
    }

    /**
     * 将 JSON 解析为 javaObject <br>
     * 注意： 如果是要解析成 List，Set，集合形式的话，请使用 parseJSONArrayToObject方法 <br>
     * 该方法不支持解析成List，Set等集合，但是支持解析为数组 <br>
     * @param json json
     * @param clazz class obj
     * @param <T> T
     * @return T
     */
    public static <T> T parseObject(JSON json, Class<T> clazz) {
        return JSON.toJavaObject(json, clazz);
    }

    /**
     * 将 T t 转换成 JSONObject <br>
     * 注意：不要将 数组，List，Set 等集合作为参数传入， JSONArray 不能 转换成 JSONObject <br>
     * 可以传入 Map Bean 是键值 类型的数据 <br>
     * @param t t
     * @param <T> T
     * @return JSONObject
     */
    public static <T> JSONObject parseJsonObject(T t) {
        return JSON.parseObject(toJsonString(t));
    }

    /**
     * json string to map
     * @param res json string
     * @param mapTypeReference map reference
     * @param <T> T
     * @return T
     */
    public static <T> T parseObject(String res, TypeReference<T> mapTypeReference) {
        return JSON.parseObject(res, mapTypeReference);
    }


    public static <T> JSONArray parseJsonArray(T[] tArray) {
        return JSON.parseArray(toJsonString(tArray));
    }

    public static <T> JSONArray parseJsonArray(List<T> list) {
        return JSON.parseArray(toJsonString(list));
    }

    /**
     * 将Set<T>  set集合 转换为JSONArray <br>
     * 注意： 要使用TreeSet的时候，注意该set有排序功能，切勿使用Object来做泛型 <br>
     * 不是说Object不可以做泛型，而是说避免使用它，以防带来存储不同数据类型的异常后果 <br>
     * @param set set
     * @param <T> T
     * @return JSONArray
     */
    public static <T> JSONArray parseJsonArray(Set<T> set) {
        return JSON.parseArray(toJsonString(set));
    }


    /**
     * JSONArray to List
     * @param jsonArray jsonArray
     * @param clazz clazz
     * @param <T> T
     * @return List
     */
    public static <T> List<T> parseList(JSONArray jsonArray, Class<T> clazz) {
        return JSON.parseArray(toJsonString(jsonArray), clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> parseSet(JSONArray jsonArray, Set<T> set) {
        return JSON.parseObject(toJsonString(jsonArray), set.getClass());
    }


    /**
     * @Author: Wjt <br></br>
     * @Date: 2019/8/10 22:09 <br></br>
     * @Params: [jsonArray, clazz] JSONArray jsonArray, Class<T> clazz <br></br>
     * @Return: T <br></br>
     * @Description: 将 JSONArray 解析成 T t，支持数组的解析 <br></br>
     */
    public static <T> T parseObject(JSONArray jsonArray, Class<T> clazz) {
        return JSON.parseObject(toJsonString(jsonArray), clazz);
    }

    /**
     * 将jsonString 解析成 Map&lt;String, T&gt; map <br>
     * 注意： 不要将单纯的 String 类型的数据 转换成JSONString 来传入，因为并非键值对模式，不会解析出Map <br>
     * @param jsonString json string
     * @param map map
     * @param <T> T
     * @return map
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> parseMap(String jsonString, Map<String, T> map) {
        return JSON.parseObject(jsonString, map.getClass());
    }
}
