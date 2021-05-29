package com.demo.spring.boot.biz.theory.structure.data.array.utils.enums;

/**
 * @author Vince Yuan
 * @date 03/29/2021
 */
public enum SortObjectEnum {

    NUMBER(1, "数字"),
    STRING(2, "字符串"),
    ;
    private int code;

    private String description;

    SortObjectEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
