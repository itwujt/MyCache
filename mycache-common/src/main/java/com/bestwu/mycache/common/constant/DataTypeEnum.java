package com.bestwu.mycache.common.constant;

import java.util.Arrays;

/**
 * Date type for cache item <br>
 *
 * @author Best Wu
 * @date 2021/7/4 2:13 <br>
 */
public enum DataTypeEnum {
    /**
     * Data type enum   string.
     */
    STRING("string", "key/value."),
    LIST_INT("list_int", "list for cache, item is int type."),
    LIST_STR("list_str", "list for cache, item is string type."),
    SET_INT("set_int", "set for cache, item is int type."),
    SET_STR("set_str", "set for cache, item is string type."),
    HASH("hash", "keyGroup, subKey, value."),
    UNKNOWN(""),
    ;

    /**
     * name
     */
    private final String name;
    /**
     * description
     */
    private String desc;

    DataTypeEnum(String name) {
        this.name = name;
    }

    DataTypeEnum(String name, String desc) {
        this(name);
        this.desc = desc;
    }

    /**
     * Match data type enum
     * @param name item name
     * @return com.bestwu.mycache.common.constant.DataTypeEnum
     */
    public static DataTypeEnum match(String name) {
        DataTypeEnum[] values = DataTypeEnum.values();
        return Arrays.stream(values)
                .filter(item -> item.name.equals(name)).findFirst().orElse(UNKNOWN);
    }
}
