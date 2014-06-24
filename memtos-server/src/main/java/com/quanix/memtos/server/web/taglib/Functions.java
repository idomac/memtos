package com.quanix.memtos.server.web.taglib;

import com.quanix.memtos.server.entity.Organization;
import com.quanix.memtos.server.service.OrganizationService;
import com.quanix.memtos.server.util.SpringUtils;

/**
 * created by lihaoquan
 *
 * Tag lib 功能类
 */
public class Functions {

    private static OrganizationService organizationService;

    public static String testOutput(Long id) {
        return "测试输出["+id+"]";
    }

    public static String organizationName(Long organizationId) {
        Organization organization = getOrganizationService().findOne(organizationId);
        if(organization == null) {
            return "";
        }
        return organization.getName();
    }


    public static OrganizationService getOrganizationService() {
        if(organizationService == null) {
            organizationService = SpringUtils.getBean(OrganizationService.class);
        }
        return organizationService;
    }
}
