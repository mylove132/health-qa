package com.jd.health.qa.common.utils;

import com.jd.health.qa.common.base.CommonCode;
import com.jd.health.qa.common.base.ICode;
import com.jd.health.qa.common.base.Result;

public class ResultUtil {

    private ResultUtil() throws Exception {
        throw new Exception("响应结果类不需要初始化");
    }

    public static  <T> Result<T> success(T data){
        return success(CommonCode.SUCCESS, data);
    }

    public static Result success(){
        return success(CommonCode.SUCCESS, null);
    }

    public static Result fail(String msg){
        CommonCode.FAIL.setCode(msg);
        return fail(CommonCode.FAIL);
    }

    private static <T> Result<T> success(ICode code, T data){
        Result<T> result = new Result<T>();
        result.setMsg(code.msg());
        result.setCode(code.code());
        result.setData(data);
        return result;
    }

    public static Result fail(ICode code){
        Result result = new Result();
        result.setMsg(code.msg());
        result.setCode(code.code());
        return result;
    }
}
