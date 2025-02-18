package com.siriforever.common.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.siriforever.common.enums.ErrorEnums;

import lombok.Data;

@Data
public class AjaxResult<T> {

    /**
     * 状态码
     **/
    private Integer code;

    /**
     * 提示信息
     **/
    private String msg;

    /**
     * 响应数据
     **/
    private T data;

    /**
     * 是否显示错误信息
     **/
    private Integer show;

    /**
     * 附加数据
     */
    private Map<String, Object> extra;

    /**
     * 无参构造
     **/
    protected AjaxResult() {
        this.extra = new HashMap<>();
    }

    /**
     * 带参构造
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param data 响应数据
     */
    public AjaxResult(Integer code, String msg, T data, Integer show) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.show = show;
        this.extra = new HashMap<>();
    }

    /**
     * 添加附加数据
     * 
     * @param key   键
     * @param value 值
     * @return this
     */
    public AjaxResult<T> put(String key, Object value) {
        this.extra.put(key, value);
        return this;
    }

    /**
     * 成功返回结果
     *
     * @return AjaxResult
     */
    public static AjaxResult<Object> success() {
        return new AjaxResult<>(ErrorEnums.SUCCESS.getCode(), ErrorEnums.SUCCESS.getMsg(), new ArrayList<>(),
                ErrorEnums.SHOW_MSG.getCode());
    }

    /**
     * 成功返回结果
     *
     * @param code 状态码
     * @return AjaxResult
     */
    public static AjaxResult<Object> success(Integer code) {
        return new AjaxResult<>(code, ErrorEnums.SUCCESS.getMsg(), new ArrayList<>(), ErrorEnums.SHOW_MSG.getCode());
    }

    /**
     * 成功返回结果
     *
     * @param msg 提示信息
     * @return AjaxResult
     */
    public static AjaxResult<Object> success(String msg) {
        return new AjaxResult<>(ErrorEnums.SUCCESS.getCode(), msg, new ArrayList<>(), ErrorEnums.SHOW_MSG.getCode());
    }

    /**
     * 成功返回结果
     *
     * @param msg 提示信息
     * @return AjaxResult
     */
    public static AjaxResult<Object> success(String msg, Integer show) {
        return new AjaxResult<>(ErrorEnums.SUCCESS.getCode(), msg, new ArrayList<>(), show);
    }

    /**
     * 成功返回结果
     *
     * @param data 响应数据
     * @return AjaxResult
     */
    public static <T> AjaxResult<T> success(T data) {
        return new AjaxResult<>(ErrorEnums.SUCCESS.getCode(), ErrorEnums.SUCCESS.getMsg(), data,
                ErrorEnums.HIDE_MSG.getCode());
    }

    /**
     * 成功返回结果
     *
     * @param code 状态码
     * @param msg  提示信息
     * @return AjaxResult
     */
    public static AjaxResult<Object> success(Integer code, String msg) {
        return new AjaxResult<>(code, msg, new ArrayList<>(), ErrorEnums.SHOW_MSG.getCode());
    }

    /**
     * 成功返回结果
     *
     * @param code 状态码
     * @param msg  提示信息
     * @return AjaxResult
     */
    public static AjaxResult<Object> success(Integer code, String msg, Integer show) {
        return new AjaxResult<>(code, msg, new ArrayList<>(), show);
    }

    public static <T> AjaxResult<T> success(Integer code, T data, Integer show) {
        return new AjaxResult<>(code, ErrorEnums.SUCCESS.getMsg(), data, show);
    }

    /**
     * 成功返回结果
     *
     * @param msg  提示信息
     * @param data 响应数据
     * @return AjaxResult
     */
    public static <T> AjaxResult<T> success(String msg, T data) {
        return new AjaxResult<>(ErrorEnums.SUCCESS.getCode(), msg, data, ErrorEnums.SHOW_MSG.getCode());
    }

    /**
     * 成功返回结果
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param data 响应数据
     * @return AjaxResult
     */
    public static <T> AjaxResult<T> success(Integer code, String msg, T data) {
        return new AjaxResult<>(code, msg, data, ErrorEnums.SHOW_MSG.getCode());
    }

    /**
     * 响应失败结果
     *
     * @param code 状态码
     * @return AjaxResult
     */
    public static AjaxResult<Object> failed(Integer code) {
        return new AjaxResult<>(code, ErrorEnums.FAILED.getMsg(), new ArrayList<>(), ErrorEnums.SHOW_MSG.getCode());
    }

    /**
     * 响应失败结果
     *
     * @param msg 提示信息
     * @return AjaxResult
     */
    public static AjaxResult<Object> failed(String msg) {
        return new AjaxResult<>(ErrorEnums.FAILED.getCode(), msg, new ArrayList<>(), ErrorEnums.SHOW_MSG.getCode());
    }

    /**
     * 响应失败结果
     *
     * @param data 响应数据
     * @return AjaxResult
     */
    public static <T> AjaxResult<T> failed(T data) {
        return new AjaxResult<T>(ErrorEnums.FAILED.getCode(), ErrorEnums.FAILED.getMsg(), data,
                ErrorEnums.SHOW_MSG.getCode());
    }

    /**
     * 响应失败结果
     *
     * @param code 状态码
     * @param msg  提示信息
     * @return AjaxResult
     */
    public static AjaxResult<Object> failed(Integer code, String msg) {
        return new AjaxResult<>(code, msg, new ArrayList<>(), ErrorEnums.SHOW_MSG.getCode());
    }

    /**
     * 响应失败结果
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param data 响应数据
     * @return AjaxResult
     */
    public static <T> AjaxResult<T> failed(Integer code, String msg, T data) {
        return new AjaxResult<>(code, msg, data, ErrorEnums.SHOW_MSG.getCode());
    }

    /**
     * 响应失败结果
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param data 响应数据
     * @return AjaxResult
     */
    public static <T> AjaxResult<T> failed(Integer code, String msg, T data, Integer show) {
        return new AjaxResult<>(code, msg, data, show);
    }
}
