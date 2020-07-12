package com.jd.health.qa.common.base;

import lombok.Data;

@Data
public class Result<T> {

    private String msg;
    private String code;
    private T data;
}
