package com.bestwu.mycache.tool;

import java.util.Arrays;
import java.util.Optional;

/**
 * <br>
 *
 * @author Best Wu
 * @date 2021/7/5 23:50 <br>
 */
public class MyCacheAssertUtil {

    public static void isNotBlank(String test) {
        if (null == test || "".equals(test)) {
            throw new IllegalArgumentException("Test string must not empty!");
        }
    }

    public static void anyNotBlank(String... testSequence) {
        if (testSequence.length > 0) {
            Optional<String> first = Arrays.stream(testSequence).filter(String::isEmpty).findFirst();
            if (first.isPresent()) {
                throw new IllegalArgumentException("Exist an empty string! It's not allowed!");
            }
        }
    }
}
