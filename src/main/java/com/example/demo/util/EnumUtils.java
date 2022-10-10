package com.example.demo.util;

public class EnumUtils {
    public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String enumName) {

        return getEnum(enumClass, enumName) != null;
    }
    public static <E extends Enum<E>> E getEnum(Class<E> enumClass, String enumName) {
        if (enumName == null) {
            return null;
        } else {
            try {
                return Enum.valueOf(enumClass, enumName);
            } catch (IllegalArgumentException var3) {
                return null;
            }
        }
    }
}
