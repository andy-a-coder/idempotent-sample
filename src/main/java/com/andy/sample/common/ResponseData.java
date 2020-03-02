package com.andy.sample.common;

import lombok.Data;

/**
 * @author andy
 *
 * @param <T>
 */
@Data
public class ResponseData<T> {
    private Boolean success = true;
    private String code = "2001";
    private String message = "request successfully";
    private static String error_code = "9999";
    private static String error_message = "system error";
    private T data;

    public ResponseData() {
        super();
    }

    public ResponseData(T data) {
        this.data = data;
    }

    public ResponseData(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseData(Boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public ResponseData(Boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    public static ResponseData<?> badRequest(String code, String message) {
        return new ResponseData<>(false, code, message);
    }
    
    public static ResponseData<?> badRequest() {
        return new ResponseData<>(false, error_code, error_message);
    }
}