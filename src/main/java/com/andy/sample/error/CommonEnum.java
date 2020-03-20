package com.andy.sample.error;

/**
 * 通用枚举定义
 * @author andy
 *
 */
public enum CommonEnum {

    REQUEST_SUCCESS("request success", "2000"),
    NO_HANDLER_EXCEPTIONS("unkown system error", "9998"),
    SYS_ERROR("system error", "9999"),
    ;

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    CommonEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public static String getErrorMsg(String code) {
        for (CommonEnum e : CommonEnum.values()) {
            if (e.getCode().equals(code)) {
                return e.message;
            }
        }
        return null;
    }
}
