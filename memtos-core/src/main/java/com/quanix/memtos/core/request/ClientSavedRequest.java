package com.quanix.memtos.core.request;

import org.apache.shiro.web.util.SavedRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : lihaoquan
 */
public class ClientSavedRequest extends SavedRequest {

    private String scheme;//当前页面使用的协议
    private String domain;
    private int port;
    private String contextPath;
    private String backUrl;


    /**
     * Constructs a new instance from the given HTTP request.
     *
     * @param request the current request to save.
     */
    public ClientSavedRequest(HttpServletRequest request) {
        super(request);
    }

    public ClientSavedRequest(HttpServletRequest request,String backUrl) {
        super(request);
        this.backUrl = backUrl;
        this.scheme = request.getScheme();
        this.domain = request.getServerName();
        this.port = request.getServerPort();
        this.contextPath = request.getContextPath();
    }


    @Override
    public String getRequestUrl() {
        //return super.getRequestUrl();
        String requestURI = getRequestURI();
        if(backUrl != null) {

        }
        return requestURI.toString();
    }



    public static String toTest() {
        return "ClientSavedRequest Tesing output";
    }
}
