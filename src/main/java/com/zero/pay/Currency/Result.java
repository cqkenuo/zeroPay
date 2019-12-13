package com.zero.pay.Currency;


import lombok.Data;

/**
 * 数据返回实体
 *
 * @author Administrator
 */
@Data
public class Result {

    private Integer code;//状态码
    private Boolean isSuccess;//状态
    private String massege;//消息
    private Object result;//数据对象
    private Long time;

    /**
     * 无参构造器
     */
    public Result() {
        super();
    }

    /**
     * 只返回状态，状态码，消息
     *
     * @param statu
     * @param code
     * @param massege
     */
    public Result(Boolean success, Integer code, String massege) {
        super();
        this.isSuccess = success;
        this.code = code;
        this.massege = massege;
        this.time = System.currentTimeMillis();
    }

    /**
     * 只返回状态，状态码，数据对象
     *
     * @param statu
     * @param code
     * @param object
     */
    public Result(Boolean success, Integer code, Object result) {
        super();
        this.isSuccess = success;
        this.code = code;
        this.result = result;
        this.time = System.currentTimeMillis();
    }

    /**
     * 返回全部信息即状态，状态码，消息，数据对象
     *
     * @param statu
     * @param code
     * @param massege
     * @param result
     */
    public Result(Boolean success, Integer code, String massege, Object result) {
        super();
        this.isSuccess = success;
        this.code = code;
        this.massege = massege;
        this.result = result;
        this.time = System.currentTimeMillis();
    }


    public static Result success(String msg, String data) {
        return new Result(true, 200, msg, data);
    }

    public static Result success() {
        return new Result(true, 200, "success", "");
    }

    public static Result success(String data) {
        return new Result(true, 200, "success", data);
    }


    public static Result error(String msg, String data) {
        return new Result(false, -1, msg, data);
    }


    public static Result error(String msg) {
        return new Result(false, -1, msg, "");
    }

    public static Result error() {
        return new Result(false, -1, "error", "");
    }


}