package com.bigchat.vo;

/**
 * Created by wang on 16-11-25.
 */
public enum HttpErrorCode {
    WEB_400("Bad request,We cannot process the request for some reason", 400),
    WEB_401("Unauthorized. The bu2access_token is missing", 401),
    WEB_403("Forbidden.this resource is not available", 403),
    WEB_404("Page does not exist", 404),
    WEB_500("Server Error.Please send the errorTrace to support@bu2trip.com", 500),

    // 登陆错误、没有登陆、权限错误
    ERROR_NOTEXIST("用户名不存在!", 1001),
    ERROR_LOGIN("密码验证错误!", 1002),
    ERROR_NOLOGIN("用户未登录!", 1003),
    ERROR_AUTHORITY("用户无权访问!", 1004),

    DB_CanNotFindByIdError("Can not find Entity id=", 3001),
    DB_CanNotFindAnyError("Can not find Any Entity", 3002),
    DB_DeleteError("Delete Entity Error id=", 3003),
    DB_ValidateError("Entity Data Format Validate fails", 3004);

    private String errorMsg;
    private Integer errorCode;

    private HttpErrorCode(String errorMsg, Integer errorCode) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @return the errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorMsg
     *            the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return "Error Code: " + this.getErrorCode() + " ErrorMsg: " + this.getErrorMsg();
    }
}
