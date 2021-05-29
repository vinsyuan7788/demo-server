package com.demo.server.application.enums.enums;

/**
 * @author Vince Yuan
 * @date 02/05/2021
 */
public enum SeasonEnum {

    SPRING("spring", "春天"),
    SUMMER("summer", "夏天"),
    FALL("fall", "秋天"),
    WINTER("winter", "冬天"),
    ;
    private String code;

    private String description;

    SeasonEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static SeasonEnum find(String code) {
        for(SeasonEnum seasonEnum : values()) {
            if (seasonEnum.getCode() == code || seasonEnum.getCode().equals(code)) {
                return seasonEnum;
            }
        }
        return null;
    }
}
