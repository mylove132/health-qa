package com.jd.health.qa.common.base;

public enum CommonCode implements ICode {

    SUCCESS("10000", "success"),
    FAIL("-1", "unknown error");

    private String msg;

    private String code;

    CommonCode(String code, String msg ){
        this.code = code;
        this.msg = msg;
    }

    public String msg() {
        return this.msg;
    }

    public String code() {
        return this.code;
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
