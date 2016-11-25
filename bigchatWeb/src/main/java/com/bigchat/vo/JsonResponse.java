package com.bigchat.vo;

/**
 * Created by wang on 16-11-25.
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Date;

/**
 * 返回数据格式
 *
 * 继承ResponseEntity 提供更方便的方法
 *
 */
public class JsonResponse extends ResponseEntity<Object> {
    public JsonResponse(Object body, MultiValueMap<String, String> headers, HttpStatus statusCode) {
        super(body, headers, statusCode);
    }

    public JsonResponse(MultiValueMap<String, String> headers, HttpStatus statusCode) {
        super(headers, statusCode);
    }

    public JsonResponse(Object body, HttpStatus statusCode) {
        super(body, statusCode);
    }

    public JsonResponse(HttpStatus statusCode) {
        super(statusCode);
    }

    /**
     * 生成一个操作成功的响应对象。
     *
     * @param object
     *            要返回的数据集合。
     * @return 包含object的响应对象，当objects为null或内容为空时会返回错误响应。
     */
    public static JsonResponse success() {
        return new JsonResponse(HttpStatus.OK);
    }

    public static <T> JsonResponse success(T object) {
        return new JsonResponse(object, HttpStatus.OK);
    }

    public static <T> JsonResponse error(HttpStatus statusCode) {
        return new JsonResponse(statusCode);
    }

    /**
     * 生成一个错误响应对象。
     *
     * @param errorMessage
     * @return
     */
    public static JsonResponse error(String errorMessage) {
        Error error = new Error(1000, errorMessage);
        return new JsonResponse(error, HttpStatus.BAD_REQUEST);
    }

    public static <T> JsonResponse error(T object) {
        return new JsonResponse(object, HttpStatus.BAD_REQUEST);
    }

    //
    /**
     * 生成一个错误响应对象。
     *
     * @param error
     *            包含错误代码和消息的ErrorCode对象
     * @return JsonResponse实例，其success属性已被置为false。
     */
    public static JsonResponse error(HttpErrorCode errorCode) {
        Error error = new Error(errorCode);
        JsonResponse result = new JsonResponse(error, HttpStatus.BAD_REQUEST);
        return result;
    }

    static class Error {
        private Integer status;
        private String msg;
        private Date createTime = new Date();

        public Integer getStatus() {
            return status;
        }

        public String getMsg() {
            return msg;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public Error(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        public Error(HttpErrorCode errorCode) {
            this.status = errorCode.getErrorCode();
            this.msg = errorCode.getErrorMsg();
        }

    }

}
