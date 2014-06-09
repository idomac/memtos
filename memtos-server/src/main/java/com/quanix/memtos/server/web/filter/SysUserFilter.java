package com.quanix.memtos.server.web.filter;

import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author : lihaoquan
 */
public class SysUserFilter extends PathMatchingFilter {


    @Override
    protected boolean onPreHandle(ServletRequest request,
        ServletResponse response, Object mappedValue) throws Exception {

        return super.onPreHandle(request, response, mappedValue);
    }
}
