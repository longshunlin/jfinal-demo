package com.mc.jfinal.utils;

/**
 * @Description
 * @Author longshunlin
 * @Date 2018/12/5
 */
public class ResultObject {
    public final static boolean SUCCESS_STATUS = true;
    public final static boolean FAIL_STATUS = false;
    //成功
    public final static int SUCCESS_CODE = 200;
    //服务器异常
    public final static int SERVER_BAD_CODE = 500;
    // 参数错误
    public final static int PARAM_BAD_CODE = 600;

    public final static String SUCCESS_MESSAGE = "";
    public final static String FAIL_MESSAGE = "未获取到数据";

    private boolean success;

    private int code;

    private String message;

    private Object data;

    public static ResultObject success() {
        return new ResultObject(SUCCESS_STATUS, null);
    }

    public static ResultObject success(Object data) {
        return new ResultObject(SUCCESS_STATUS, data);
    }

    public static ResultObject error() {
        return new ResultObject(FAIL_STATUS, SERVER_BAD_CODE, FAIL_MESSAGE);
    }

    public static ResultObject error(String errorMessage) {
        return new ResultObject(FAIL_STATUS, SERVER_BAD_CODE, errorMessage);
    }

    public static ResultObject error(int errorCode, String errorMessage) {
        return new ResultObject(FAIL_STATUS, errorCode, errorMessage);
    }

    public static ResultObject error(int errorCode) {
        return new ResultObject(FAIL_STATUS, errorCode, FAIL_MESSAGE);
    }

    public ResultObject() {
    }

    public ResultObject(boolean success, int code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultObject(Object data) {
        this.success = SUCCESS_STATUS;
        this.code = SUCCESS_CODE;
        this.message = "";
        this.data = data;
    }

    public ResultObject(boolean success, Object data) {
        this.success = success;
        if (success) {
            this.code = SUCCESS_CODE;
        } else {
            this.code = SERVER_BAD_CODE;
        }
        this.message = "";
        this.data = data;
    }

    public ResultObject(boolean success) {
        this.success = success;
        if (success) {
            this.code = SUCCESS_CODE;
            this.message = SUCCESS_MESSAGE;
        } else {
            this.code = SERVER_BAD_CODE;
            this.message = FAIL_MESSAGE;
        }
    }

    public ResultObject(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public ResultObject(boolean success, int code) {
        this.success = success;
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
