package com.ruiqin.permission.network.entity;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class RespLogin {

    /**
     * status : 1
     * message :
     * result : {"Access_Token":"yonVlxEYBGHlVFQMYCj5snSJjTf4sIQBKlqFlNLKc7n7qM4FZ0XtTxRq7YrAeggLrxJIplEJI1gtRVWj3GC97g=="}
     */

    private int status;
    private String message;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * Access_Token : yonVlxEYBGHlVFQMYCj5snSJjTf4sIQBKlqFlNLKc7n7qM4FZ0XtTxRq7YrAeggLrxJIplEJI1gtRVWj3GC97g==
         */

        private String Access_Token;

        public String getAccess_Token() {
            return Access_Token;
        }

        public void setAccess_Token(String Access_Token) {
            this.Access_Token = Access_Token;
        }
    }
}
