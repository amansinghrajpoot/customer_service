package com.project.customerservice.interceptors.auth;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class AuthInterceptor {
    @Before("execution(* com.project.customerservice.controllers.CustomerController.*(..))")
    public void authenticate(){
        validateToken(
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
        );
    }

    private void validateToken(HttpServletRequest request){
    }
}
