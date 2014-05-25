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
            if(backUrl.toLowerCase().startsWith("http://")
                    || backUrl.toLowerCase().startsWith("https://")) {
                return backUrl;
            } else if(!backUrl.startsWith(contextPath)) {
                requestURI = contextPath + backUrl;
            } else {
                requestURI = backUrl;
            }
        }

        StringBuilder requestUrl = new StringBuilder(scheme);//4
        requestUrl.append("://");
        requestUrl.append(domain);

        if("http".equalsIgnoreCase(scheme) && port != 80) {
            requestUrl.append(":").append(String.valueOf(port));
        } else if("https".equalsIgnoreCase(scheme) && port != 443) {
            requestUrl.append(":").append(String.valueOf(port));
        }

        requestUrl.append(requestURI);
        if (backUrl == null && getQueryString() != null) {
            requestUrl.append("?").append(getQueryString());
        }
        return requestURI.toString();
    }



    public static String toTest() {
        return "ClientSavedRequest Tesing output";
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }
}
