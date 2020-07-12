package com.jd.health.qa.web.core.resolver;

import com.jd.health.qa.common.annotation.LoginAuth;
import com.jd.health.qa.framework.util.ShiroUtils;
import com.jd.health.qa.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *  登录用户参数解析器
 * @author LErry.li
 * Date: 2018-12-08
 * Time: 17:41
 */
@Slf4j
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        final Method method = methodParameter.getMethod();
        final Class<?> clazz = Objects.requireNonNull(methodParameter.getMethod()).getDeclaringClass();

        boolean isHasLoginAuthAnn = clazz.isAnnotationPresent(LoginAuth.class) || Objects.requireNonNull(method).isAnnotationPresent(LoginAuth.class);
        boolean isHasLoginUserParameter = methodParameter.getParameterType().isAssignableFrom(SysUser.class);

        return isHasLoginAuthAnn && isHasLoginUserParameter;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory){
        return ShiroUtils.getSysUser();
    }
}
