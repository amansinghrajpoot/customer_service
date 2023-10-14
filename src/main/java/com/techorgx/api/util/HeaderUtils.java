package com.techorgx.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.StringJoiner;

@Component
public class HeaderUtils {

    @Autowired
    ObjectMapper objectMapper;

    public static final Logger logger = LoggerFactory.getLogger(HeaderUtils.class);

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

    public String formatRequest(HttpServletRequest request) {
        ObjectNode rootNode = objectMapper.createObjectNode();

        rootNode.put("Request Method", request.getMethod());
        rootNode.put("Request URI", request.getRequestURI());
        rootNode.put("Request URL", request.getRequestURL().toString());
        rootNode.put("Protocol", request.getProtocol());
        rootNode.put("Remote Host", request.getRemoteHost());
        rootNode.put("Remote Address", request.getRemoteAddr());

        // Headers
        Collections.list(request.getHeaderNames()).forEach(headerName -> {
            String headerValue = request.getHeader(headerName);
            rootNode.put("Header " + headerName, headerValue);
        });

        // Parameters
        request.getParameterMap().forEach((paramName, paramValues) -> {
            StringJoiner paramJoiner = new StringJoiner(", ");
            for (String paramValue : paramValues) {
                paramJoiner.add(paramValue);
            }
            rootNode.put("Parameter " + paramName, paramJoiner.toString());
        });

        try {
            return objectMapper.writeValueAsString(rootNode);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "{}";
        }
    }
}
