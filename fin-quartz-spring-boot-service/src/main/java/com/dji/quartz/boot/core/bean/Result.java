package com.dji.quartz.boot.core.bean;

public class Result<T> extends BaseDTO {

    /**
     *
     */
    private static final long serialVersionUID = -2038224139934383529L;

    private static final String SUCC_CODE = "S0000";

    private static final String UNKNOWN_FAIL_CODE = "E0000";

    private T data;

    private String code;

    private String msg;

    private boolean success;

    public Result() {
        super();
    }

    public Result(boolean success, String code) {
        super();
        this.success = success;
        this.code = code;
    }

    public Result(T data, boolean success, String code, String msg) {
        super();
        this.data = data;
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    /**
     * only return success
     *
     * @return
     */
    public static Result<Void> success() {
        return success(null, null);
    }

    /**
     * only return data
     *
     * @param data
     * @param <R>
     * @return
     */
    public static <R> Result<R> success(R data) {
        return success(data, null);
    }

    /**
     * return data & msg
     *
     * @param data
     * @param msg
     * @param <R>
     * @return
     */
    public static <R> Result<R> success(R data, String msg) {
        return new Result<>(data, true, SUCC_CODE, msg);
    }

    /**
     * only return msg
     *
     * @param msg
     * @return
     */
    public static Result<Void> successMsg(String msg) {
        return success(null, msg);
    }


    /**
     * return error code
     *
     * @param code
     * @param <R>
     * @return
     */
    public static <R> Result<R> failure(String code) {
        return failure(code, null);
    }

    /**
     * return code and msg
     *
     * @param code
     * @param msg
     * @param <R>
     * @return
     */
    public static <R> Result<R> failure(String code, String msg) {
        return failure(null, code, msg);
    }


    /**
     * return data code and msg
     *
     * @param data
     * @param code
     * @param msg
     * @param <R>
     * @return
     */
    public static <R> Result<R> failure(R data, String code, String msg) {
        return new Result<>(data, false, code, msg);
    }

    /**
     * return error msg
     *
     * @param msg
     * @param <R>
     * @return
     */
    public static <R> Result<R> failureMsg(String msg) {
        return failure(UNKNOWN_FAIL_CODE, msg);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}