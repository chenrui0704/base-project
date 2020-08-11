package com.base.frame.util;

import java.util.Collection;
import java.util.Map;

/**
 * 验证工具类
 *
 */
public class VerifyUtil {

    public static boolean stringIsEmpty(String o) {
        return isNull(o) || 0 == o.length();
    }

    public static boolean stringTrimIsEmpty(String o) {
        return isNull(o) || 0 == o.trim().length();
    }

    public static boolean numberIsEmpty(Number o) {
        return isNull(o) || 0 == o.doubleValue();
    }

    public static boolean collectionIsEmpty(Collection o) {
        return isNull(o) || 0 == o.size();
    }

    public static boolean mapIsEmpty(Map o) {
        return isNull(o) || 0 == o.size();
    }

    public static boolean isEmpty(Object o) {
        if (isNull(o)) {
            return true;
        }
        if (o instanceof Map) {
            return 0 == ((Map) o).size();
        } else if (o instanceof Collection) {
            return 0 == ((Collection) o).size();
        } else if (o instanceof String) {
            return 0 == ((String) o).length();
        } else if (o instanceof Number) {
            return 0 == ((Number) o).doubleValue();
        }
        return false;
    }

    public static boolean isNull(Object o) {
        return null == o;
    }

}