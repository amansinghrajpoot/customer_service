package com.project.customerservice.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class HeaderUtils {

    public HttpServletRequest getHttpRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            return attributes.getRequest();
        } else {
            throw new IllegalStateException("No thread-bound request found.");
        }
    }

    public String getClientId(){
        HttpServletRequest request = getHttpRequest();
        return request.getHeader("client-id");
    }

    public String getToken(){
        HttpServletRequest request = getHttpRequest();
        return request.getHeader("Authorization");
    }
}
