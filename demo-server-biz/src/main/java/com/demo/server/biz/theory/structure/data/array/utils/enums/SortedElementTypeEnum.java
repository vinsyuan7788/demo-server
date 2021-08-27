package com.demo.server.biz.theory.structure.data.array.utils.enums;

/**
 *  This is an enumeration that represents the type of elements to be sorted in an array
 *
 * @author Vince Yuan
 * @date 03/29/2021
 */
public enum SortedElementTypeEnum {

    NUMBER("number", "数字"),
    STRING("string", "字符串"),
    ;
    private String code;

    private String description;

    SortedElementTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public static SortedElementTypeEnum findByCode(String code) {
        for (SortedElementTypeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
